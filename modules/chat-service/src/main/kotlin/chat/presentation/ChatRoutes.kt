package com.example.chat.presentation

import com.example.chat.domain.services.ChatService
import com.example.chat.domain.services.WebSocketConnectionManager
import com.example.chat.presentation.models.MessageDto
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import org.koin.core.component.getScopeName
import org.koin.ktor.ext.getKoin
import org.litote.kmongo.json
import utils.ApiError
import utils.extensionFunctions.getUserId
import utils.extensionFunctions.respondError
import utils.extensionFunctions.respondSuccess
import utils.handleException


fun Route.chatRoutes(webSocketConnectionManager: WebSocketConnectionManager = getKoin().get(), chatService: ChatService = getKoin().get(), gson: Gson = getKoin().get()) {
    suspend fun DefaultWebSocketSession.processMessage(senderId: String, frame: Frame.Text) {
        try {
            val message = gson.fromJson(frame.readText(), MessageDto::class.java)
            chatService.sendMessage(senderId, message.roomId, message.content)
        } catch (e: Exception) {
            application.log.error("Error processing message: ", e)
            val response = handleException(e)
            send(Frame.Text(response.error.json))
        }
    }


     authenticate("auth-jwt"){
        webSocket("/chat") {
            val senderId = call.getUserId()
            webSocketConnectionManager.addSession(senderId, this)
            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        processMessage(senderId, frame)
                    }
                }
            } catch (e: ClosedReceiveChannelException) {
                application.log.info("WebSocket closed by client")
            } catch (e: Exception) {
                application.log.error("WebSocket error: ", e)
                send(Frame.Text(handleException(e).error.json))
            } finally {
                webSocketConnectionManager.removeSession(senderId)
            }
        }

        // Endpoint to get all messages in the room with the provided id.
        get("/rooms/{room_id}/all"){
            val roomId = call.parameters["room_id"] ?: throw MissingRequestParameterException("room_id")
            val userId = call.getUserId()
            val messages = chatService.getRoomMessages(roomId, userId)

            call.respondSuccess(messages)
        }

        post("/rooms"){
            val params = call.parameters
            val productId = params["product_id"] ?: throw Exception("product_id id is required")
            val creatorId = call.getUserId()
            val receiverId = params["receiver_id"] ?: throw Exception("receiver_id is required")

            val createdRoom = chatService.createRoom(productId, listOf(creatorId, receiverId))

            call.respondSuccess(createdRoom)
        }


        // Endpoint to get all rooms belonging to the logged-in user
        get("/rooms") { // session can't be null, or the route wouldn't be accessible
            val userId = call.getUserId()
            val rooms =  chatService.getAllRooms(userId)
            call.respondSuccess(rooms)
        }

    }



}


package com.example.chat.presentation

import com.example.chat.domain.services.ChatService
import com.example.chat.domain.services.WebSocketConnectionManager
import com.example.chat.presentation.models.MessageDto
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.koin.ktor.ext.getKoin
import utils.extensionFunctions.getUserId


fun Route.chatRoutes(webSocketConnectionManager: WebSocketConnectionManager = getKoin().get(), chatService: ChatService = getKoin().get(), gson: Gson = getKoin().get()) {
    webSocket("/chat/{user_id}") {
        val userId = call.getUserId()
        webSocketConnectionManager.addSession(userId, this)

        try {
            for (frame in incoming) {
                if(frame is Frame.Text){
                    val message =  gson.fromJson(frame.readText(), MessageDto::class.java)
                    val response = chatService.sendMessage(userId, message.roomId, message.content)
                }
            }
        } finally {
            webSocketConnectionManager.removeSession(userId)
        }
    }

    // Endpoint to get all messages in the room with the provided id.
    get("/rooms/{roomId}/all"){
        val id = call.parameters["roomId"] ?: throw Exception("roomId is required")
        val userId = call.getUserId()
        val messages = chatService.getRoomMessages(id)

        call.respond(HttpStatusCode.OK,messages)
    }

    post("/rooms"){
        val params = call.receiveParameters()
        val productId = params["product_id"] ?: throw Exception("product_id id is required")
        val creatorId = params["creator_id"] ?: throw Exception("creator_id is required")
        val receiverId = params["receiver_id"] ?: throw Exception("receiver_id is required")

        val createdRoom = chatService.createRoom(productId, listOf(creatorId, receiverId))

        call.respond(status = HttpStatusCode.Created,  createdRoom)
    }


    // Endpoint to get all rooms belonging to the logged-in user
    get("/rooms") { // session can't be null, or the route wouldn't be accessible
        val userId = call.getUserId()
        val rooms =  chatService.getAllRooms(userId)
        call.respond(rooms)
    }

}




package com.example.chat.domain.services

import com.example.chat.data.models.Room
import com.example.chat.domain.repositories.MessageRepository
import com.example.chat.domain.repositories.RoomRepository
import com.example.chat.data.models.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import utils.UnauthorizedAccessException

class ChatService(
    private val roomRepository: RoomRepository,
    private val messageRepository: MessageRepository,
    private val webSocketConnectionManager: WebSocketConnectionManager
) {

    suspend fun createRoom(productId: String, userIds: List<String>): Room {
        val newRoom = Room(userIds = userIds, productId = productId)
        roomRepository.createRoom(newRoom)
        return newRoom
    }

    suspend fun deleteRoom(roomId: String) {
        roomRepository.deleteRoom(roomId)
    }


    suspend fun sendMessage(senderId: String, roomId: String, messageContent: String) {
        val room = roomRepository.getRoomById(roomId)
        if (!room.userIds.contains(senderId)) {
            throw Exception("User not in the room")
        }

        //create a message
        val message = Message(
            senderId = senderId,
            roomId = roomId,
            content = messageContent,
            timestamp = System.currentTimeMillis()
        )
        //save massage in db
       messageRepository.insertMessage(message)

        //notify the users
        room.userIds.forEach { userId ->
            val session = webSocketConnectionManager.getSession(userId)
            session?.send(Frame.Text(Json.encodeToString(message)))
        }

    }

    suspend fun getRoomMessages(roomId: String, userId: String): List<Message> {
        return if(roomRepository.getRoomById(roomId).userIds.contains(userId)){
            messageRepository.getMessagesByRoomId(roomId)
        }else{
            throw UnauthorizedAccessException("You don't have access to this conversation")
        }
    }

    suspend fun getAllRooms(id: String): List<Room> {
        return roomRepository.getRoomsByMemberId(id)
    }
}

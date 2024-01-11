package com.example.chat.domain.services

import com.example.chat.data.models.Room
import com.example.chat.domain.repositories.MessageRepository
import com.example.chat.domain.repositories.RoomRepository
import com.example.chat.data.models.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
        //check if user can send a message in this room
        val room = roomRepository.getRoomById(roomId) ?: throw Exception("Room not found")
        if (!room.userIds.contains(senderId)) {
            throw Exception("User not in room")
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
        room.userIds.forEach {
            val session = webSocketConnectionManager.getSession(it)
            session?.send(Frame.Text(Json.encodeToString(message)))
        }

    }

    suspend fun getRoomMessages(roomId: String): List<Message> {
        return messageRepository.getMessagesByRoomId(roomId)
    }

    suspend fun getAllRooms(id: String): List<Room> {
        return roomRepository.getRoomsByMemberId(id)
    }
}

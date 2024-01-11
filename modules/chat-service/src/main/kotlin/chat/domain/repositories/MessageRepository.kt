package com.example.chat.domain.repositories

import com.example.chat.data.models.Message

interface MessageRepository {

    suspend fun getAllMessages(): List<Message>

    suspend fun insertMessage(message: Message)
    suspend fun getMessageById(id: String): Message?
    suspend fun getMessagesByIds(ids: List<String>): List<Message>
    suspend fun getMessagesByRoomId(id: String): List<Message>
    suspend fun updateMessage(message: Message)
    suspend fun deleteMessageById(id: String)
}
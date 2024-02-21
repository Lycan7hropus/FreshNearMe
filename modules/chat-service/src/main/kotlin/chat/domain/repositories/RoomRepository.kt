package com.example.chat.domain.repositories

import com.example.chat.data.models.Room

interface RoomRepository {
    suspend fun deleteRoom(id: String)
    suspend fun updateRoom(room: Room)
    suspend fun getRoomsByMemberId(id: String): List<Room>
    suspend fun getRoomById(id: String): Room
    suspend fun createRoom(room: Room): Room
    fun removeUserFromRoom(userId: String, roomId: String): Nothing?

}
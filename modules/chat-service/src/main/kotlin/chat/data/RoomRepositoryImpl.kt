package com.example.chat.data

import com.example.chat.data.models.Room
import com.example.chat.domain.repositories.RoomRepository
import org.litote.kmongo.contains
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class RoomRepositoryImpl (private val roomsCollection: CoroutineCollection<Room>): RoomRepository {
    override suspend fun createRoom(room: Room): Room {
        return if(roomsCollection.insertOne(room).wasAcknowledged()) room else throw Exception("mock")
    }

    override fun removeUserFromRoom(userId: String, roomId: String): Nothing? {
        TODO("Not yet implemented")
    }

    override suspend fun getRoomById(id: String): Room? {
        return roomsCollection.findOneById(id)
    }

    override suspend fun getRoomsByMemberId(id: String): List<Room> {
        return roomsCollection.find(Room::userIds contains id).toList()
    }

    override suspend fun updateRoom(room: Room) {
        roomsCollection.replaceOne(Room::roomId eq room.roomId,room)
    }


    override suspend fun deleteRoom(id: String) {
        //TODO delete all messages belonging to room
        roomsCollection.deleteOneById(id)
    }
}
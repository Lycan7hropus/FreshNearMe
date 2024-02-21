package com.example.chat.data

import com.example.chat.domain.repositories.MessageRepository
import com.example.chat.data.models.Message
import com.mongodb.MongoWriteException
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.`in`
import utils.DatabaseOperationException

class MessageRepositoryImpl(private val messagesCollection: CoroutineCollection<Message>): MessageRepository {
    override suspend fun getAllMessages(): List<Message> {
        return messagesCollection.find().toList()
    }


    override suspend fun insertMessage(message: Message) {
        val result = messagesCollection.insertOne(message)
        if(!result.wasAcknowledged()) throw DatabaseOperationException("Couldnt save a meessage")
    }

    override suspend fun getMessageById(id: String): Message? {
        return messagesCollection.findOneById(id)
    }

    override suspend fun getMessagesByIds(ids: List<String>): List<Message> {
        return messagesCollection.find(Message::id `in` ids).toList()
    }

    override suspend fun getMessagesByRoomId(id: String): List<Message> {
        return messagesCollection.find(Message::roomId eq id).toList()
    }

    override suspend fun updateMessage(message: Message) {
        messagesCollection.replaceOne(Message::id eq message.id,message)
    }

    override suspend fun deleteMessageById(id: String) {
        messagesCollection.deleteOneById(id)
    }
}
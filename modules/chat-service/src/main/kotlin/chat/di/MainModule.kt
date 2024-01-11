package com.example.chat.di

import com.example.chat.data.RoomRepositoryImpl
import com.example.chat.data.models.Room
import com.example.chat.domain.repositories.MessageRepository
import com.example.chat.domain.repositories.RoomRepository
import com.example.chat.data.MessageRepositoryImpl
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import com.example.chat.data.models.Message
import com.example.chat.domain.services.ChatService
import com.example.chat.domain.services.WebSocketConnectionManager
import com.google.gson.Gson
import database.DatabaseProvider
import database.MongoDatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val chatModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("fresh_near_me_chat_db") }

    single<CoroutineCollection<Message>>(named("MessageCollection")) {
        val messagesCollection: CoroutineCollection<Message> = get<DatabaseProvider>().database.getCollection("messages")
        CoroutineScope(Dispatchers.IO).launch {
            messagesCollection.createIndex(Indexes.ascending("room_id"))
        }
        messagesCollection
    }


    single<CoroutineCollection<Room>>(named("RoomCollection")) {
        val indexOptions = IndexOptions().background(true)
        val roomCollection: CoroutineCollection<Room> = get<DatabaseProvider>().database.getCollection("rooms")
        CoroutineScope(Dispatchers.IO).launch {
            roomCollection.createIndex(Indexes.ascending("user_ids"), indexOptions)
        }
        roomCollection
    }

    single<MessageRepository> {
        MessageRepositoryImpl(get(named("MessageCollection")))
    }

    single<RoomRepository> {
        RoomRepositoryImpl(get(named("RoomCollection")))
    }

    single<Gson> {
        Gson()
    }

    single<WebSocketConnectionManager> {
        WebSocketConnectionManager()
    }

    single<ChatService> {
        ChatService(get(),get(), get())
    }
}
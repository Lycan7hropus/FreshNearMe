package com.example

import com.example.database.DatabaseProvider
import com.example.di.initKoin
import com.example.models.Offer
import com.example.models.User
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.mongodb.client.model.Indexes
import com.mongodb.reactivestreams.client.MongoClient
import io.ktor.server.application.*
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ktor.ext.getKoin
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)

}

// Your Application.module where you configure your Ktor application
fun Application.module() {
    initKoin()
    configureSerialization()
    configureSecurity()
    configureRouting()

}

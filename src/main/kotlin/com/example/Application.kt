package com.example

import com.example.database.DatabaseProvider
import com.example.di.initKoin
import com.example.models.User
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.mongodb.reactivestreams.client.MongoClient
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

// Your Application.module where you configure your Ktor application
fun Application.module() {
    initKoin()
    configureSerialization()
    configureSecurity()
    configureRouting()

    // Use the DatabaseProvider to access the database
    val databaseProvider: DatabaseProvider by inject()
    val database = databaseProvider.database

}

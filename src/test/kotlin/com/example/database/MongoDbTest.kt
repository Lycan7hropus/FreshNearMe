package com.example.database

import com.example.features.category.domain.Category
import com.example.features.user.domain.models.User
import com.google.gson.Gson
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.ktor.test.dispatcher.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.bson.Document
import org.junit.After
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.qualifier.named
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection
import kotlin.test.AfterTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBTest {
    private lateinit var server: TestApplicationEngine
    private lateinit var mongoClient: CoroutineClient
    private lateinit var db: CoroutineDatabase
    var collectionName = "names"
    private val httpClient: HttpClient get() = server.client

    init {
        mongoClient = KMongo.createClient().coroutine
        db = mongoClient.getDatabase("testEngineTests")

        server = TestApplicationEngine(createTestEnvironment {
            developmentMode = false // to prevent a bug with a class loader in Ktor 2.0.2
            module {
                routes(db, collectionName)
            }
        })

        server.start(wait = false)
    }

    @AfterAll
    fun dropCollection() {
        runBlocking { db.getCollection<Name>(collectionName).drop() }
    }

    @Test
    fun checkSomeNames() = testSuspend {
        db.getCollection<Name>(collectionName).insertMany(
            listOf(
                Name("John"),
                Name("Mike"),
                Name("Eddie"),
            )
        )

        assertEquals("John,Mike,Eddie", httpClient.get("/").bodyAsText())
    }

    @Test
    fun checkNoNames() = testSuspend {
        dropCollection()
        assertEquals("", httpClient.get("/").bodyAsText())
    }
}

fun Application.routes(db: CoroutineDatabase, collectionName: String) {
    routing {
        get("/") {
            runBlocking {
                val collection = db.getCollection<Name>(collectionName)
                val names = collection.find().toList()

                call.respondText {
                    names.joinToString(separator = ",") { it.value }
                }
            }
        }
    }
}

data class Name(var value: String)
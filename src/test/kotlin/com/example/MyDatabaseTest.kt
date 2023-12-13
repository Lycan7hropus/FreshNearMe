package com.example

import com.mongodb.reactivestreams.client.MongoClients
import kotlinx.coroutines.runBlocking
import org.bson.Document
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class MyDatabaseTests: KoinTest {
    companion object {
        val mongoContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))

        @BeforeAll
        @JvmStatic
        fun setup() {
            mongoContainer.start()
        }
    }

    @Test
    fun testCreateDatabase() {
        val mongoUri = mongoContainer.replicaSetUrl
        println(mongoUri)
        runBlocking {
            //
        // Połączenie z MongoDB
        val mongoClient: CoroutineClient = MongoClients.create(mongoUri).coroutine

        // Utworzenie nowej bazy danych
        val database: CoroutineDatabase = mongoClient.getDatabase("myTestDatabase")

        // Utworzenie kolekcji
        database.createCollection("testCollection")

        // Dodanie dokumentu do kolekcji
        val collection = database.getCollection<Document>("testCollection")
        val doc = Document("name", "Test Name").append("value", 123)
        collection.insertOne(doc)

        // Sprawdzenie, czy kontener działa i czy dokument został dodany
        Assertions.assertTrue(mongoContainer.isRunning)
        Assertions.assertEquals(1, collection.countDocuments())

        mongoClient.close()
        }
    }

    @Test
    fun testSomethingWithDatabase() {
        val mongoUri = mongoContainer.replicaSetUrl
        println(mongoUri)
        Assertions.assertTrue(mongoContainer.isRunning)
    }
}
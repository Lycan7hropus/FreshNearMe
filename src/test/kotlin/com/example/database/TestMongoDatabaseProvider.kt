package com.example.database

import org.junit.jupiter.api.BeforeAll
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class TestMongoDatabaseProvider(private val clientName: String) : DatabaseProvider {
    val mongoContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10")).apply { start() }

    override val initializeName: String
        get() = clientName

    override val mongoClient: CoroutineClient
        get() = KMongo.createClient(mongoContainer.replicaSetUrl).coroutine

    override val database: CoroutineDatabase
        get() = mongoClient.getDatabase(initializeName)
}


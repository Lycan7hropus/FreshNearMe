package database

import database.DatabaseProvider
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class MongoDatabaseProvider(private val clientName: String) : DatabaseProvider {
    private val mongoUri: String? = System.getenv("MONGO_URI")

    override val initializeName: String
        get() = clientName

    override val mongoClient: CoroutineClient
        get() = mongoUri?.let { KMongo.createClient(it).coroutine } ?: KMongo.createClient().coroutine

    override val database: CoroutineDatabase
        get() = mongoClient.getDatabase(initializeName)
}


package database

import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase

interface DatabaseProvider {
    val initializeName: String

    val mongoClient: CoroutineClient

    val database: CoroutineDatabase
}
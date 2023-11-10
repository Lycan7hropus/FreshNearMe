package com.example.database

import com.example.models.Offer
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import kotlin.reflect.KClass

interface DatabaseProvider {
    val initializeName: String

    val mongoClient: CoroutineClient

    val database: CoroutineDatabase

}
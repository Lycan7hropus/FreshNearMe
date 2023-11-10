package com.example.plugins

import com.example.database.DatabaseProvider
import com.example.models.Offer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.cleanDbRoute(databaseProvider: DatabaseProvider) {
    get("/clean/db") {
        // Use the provided databaseProvider to access the collection
        val offersCollection = databaseProvider.database.getCollection<Offer>("offers")
        // Perform the deletion operation
        offersCollection.deleteMany()
        // Respond to the request indicating success
        call.respond(HttpStatusCode.OK, "Collection cleaned")
    }
}
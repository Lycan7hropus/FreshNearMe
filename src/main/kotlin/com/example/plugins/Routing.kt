package com.example.plugins

import com.example.database.DatabaseProvider
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.models.Offer
import com.mongodb.client.model.Indexes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineCollection
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {

        }

        val databaseProvider: DatabaseProvider by inject()
        cleanDbRoute(databaseProvider)

        get("/test") {
            call.respondText("test!")
        }
        // Route for user registration
        post("/register") {
            call.respondText("register!")
        }

        // Route for user login
        post("/login") {
            // Handle user login logic here
        }



        // Route for getting a list of offers posted by a specific user
        get("/user/{userId}/offers") {
            // Handle fetching offers for a specific user here
        }

        offerRoutes()
        categoryRoutes()

        // Route for getting a user's wishlist
        get("/user/{userId}/wishlist") {
            // Handle fetching a user's wishlist here
        }

        // Route for getting user information by ID
        get("/user/{userId}") {
            // Handle fetching user information here
        }
    }

}

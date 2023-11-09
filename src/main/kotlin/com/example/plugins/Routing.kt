package com.example.plugins

import com.example.models.FoodCategory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            var category = FoodCategory.Dairy.Milk.CowMilk

            if(category is FoodCategory.Dairy.Milk){
                call.respondText("true!")
            }else{
                call.respondText("false!")
            }
        }
        // Route for user registration
        post("/register") {
            // Handle user registration logic here
        }

        // Route for user login
        post("/login") {
            // Handle user login logic here
        }

        // Route for adding an offer
        post("/offers") {
            // Handle adding a new offer here
        }

        // Route for getting a list of all offers
        get("/offers") {
            // Handle fetching all offers here
        }

        // Route for getting a single offer by ID
        get("/offers/{id}") {
            // Handle fetching a single offer by ID here
        }

        // Route for getting a list of offers posted by a specific user
        get("/user/{userId}/offers") {
            // Handle fetching offers for a specific user here
        }

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

package com.example.features.user.presentation

import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.features.user.domain.usecases.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(getUserOffersUseCase: GetUserOffersUseCase, getUserUseCase: GetUserUseCase, getUserWishlistUseCase: GetUserWishListUseCase, saveUserUseCase: SaveUserUseCase, updateUserUseCase: UpdateUserUseCase) {

    get("/user/{userId}") {
        try {
            val userId = call.parameters["userId"] ?: return@get call.respondText("Missing or malformed userId", status = HttpStatusCode.BadRequest)
            val user = getUserUseCase.invoke(userId)
            call.respond(user)
        }catch (e: Exception){
            call.respondText(e.message ?: "An error occurred", status = HttpStatusCode.BadRequest)
        }
    }

    // W ROUTE SPRAWDZASZ CZY COS NIE JEST NULLEM I CZY MA POPRAWNY FORMART, W USE CASE CZY DAME SA PRAWIDLOWE
    // Pobieranie listy życzeń użytkownika
    get("/user/{userId}/wishlist") {
        try {
            val userId = call.parameters["userId"] ?: return@get call.respondText("Missing or malformed userId", status = HttpStatusCode.BadRequest)
            val wishListResponse = getUserWishlistUseCase.invoke(userId)
            call.respond(wishListResponse)
        } catch (e: Exception) {
            call.respondText(e.message ?: "An error occurred", status = HttpStatusCode.BadRequest)
        }
    }


    // Pobieranie ofert użytkownika
    get("/user/{userId}/offers") {
        try {
            val userId = call.parameters["userId"] ?: return@get call.respondText("Missing or malformed userId", status = HttpStatusCode.BadRequest)
            val offersResponse = getUserOffersUseCase.invoke(userId)
            call.respond(offersResponse)
        } catch (e: Exception) {
            call.respondText(e.message ?: "An error occurred", status = HttpStatusCode.BadRequest)
        }
    }

    // Aktualizacja danych użytkownika
    put("/user/{userId}") {
       try {
           val user = call.receive<User>()
           val success = updateUserUseCase.invoke(user)
           call.respond(success)
       }catch (e: Exception){
           call.respondText(e.message ?: "An error occurred", status = HttpStatusCode.BadRequest)
       }
    }


    // Zapisywanie użytkownika
    post("/user") {
        try {
            val userInfo = call.receive<UserRequest>()
            val success = saveUserUseCase.invoke(userInfo)
            call.respond(success)
        }catch (e: Exception){
            call.respondText(e.message ?: "An error occurred", status = HttpStatusCode.BadRequest)
        }
    }
}

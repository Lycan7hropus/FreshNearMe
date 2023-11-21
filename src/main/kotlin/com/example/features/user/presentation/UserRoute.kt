package com.example.features.user.presentation

import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.features.user.domain.usecases.*
import com.example.utils.respondError
import com.example.utils.respondSuccess
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(getUserOffersUseCase: GetUserOffersUseCase, getUserUseCase: GetUserUseCase, getUserWishlistUseCase: GetUserWishListUseCase, saveUserUseCase: SaveUserUseCase, updateUserUseCase: UpdateUserUseCase) {

    get("/user/{userId}") {
        val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
        val user = getUserUseCase.invoke(userId)
        call.respondSuccess(data = user)
    }

    // W ROUTE SPRAWDZASZ CZY COS NIE JEST NULLEM I CZY MA POPRAWNY FORMART, W USE CASE CZY DAME SA PRAWIDLOWE
    get("/user/{userId}/wishlist") {
        val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
        val wishListResponse = getUserWishlistUseCase.invoke(userId)
        call.respondSuccess(data = wishListResponse)
    }

    get("/user/{userId}/offers") {
        val userId = call.parameters["userId"]  ?: throw MissingRequestParameterException("userId")
        val offersResponse = getUserOffersUseCase.invoke(userId)
        call.respondSuccess(data = offersResponse)
    }


    put("/user") {
        val user = call.receive<User>()
        val success = updateUserUseCase.invoke(user)
        call.respondSuccess(data = success)
    }


    post("/user") {
        val userInfo = call.receive<UserRequest>()
        val user = saveUserUseCase.invoke(userInfo)
        call.respondSuccess(code = HttpStatusCode.Created, data = user)
    }



}


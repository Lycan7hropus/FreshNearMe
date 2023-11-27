package com.example.features.user.presentation

import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.features.user.domain.usecases.*
import com.example.utils.respondError
import com.example.utils.respondSuccess
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javax.naming.AuthenticationException

fun Route.userRoutes(getUserOffersUseCase: GetUserOffersUseCase, getUserUseCase: GetUserUseCase, getUserWishlistUseCase: GetUserWishListUseCase, saveUserUseCase: SaveUserUseCase, updateUserUseCase: UpdateUserUseCase) {

    get("/user/{userId}") {
        val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
        val user = getUserUseCase.invoke(userId)
        call.respondSuccess(data = user)
    }

    // W ROUTE SPRAWDZASZ CZY COS NIE JEST NULLEM I CZY MA POPRAWNY FORMART, W USE CASE CZY DAME SA PRAWIDLOWE
    get("/user/{userId}/offers") {
        val userId = call.parameters["userId"]  ?: throw MissingRequestParameterException("userId")
        val offersResponse = getUserOffersUseCase.invoke(userId)
        call.respondSuccess(data = offersResponse)
    }

    authenticate("auth-bearer")  {
        get("/user/wishlist") {
            val userId = call.principal<UserIdPrincipal>()?.name ?: throw AuthenticationException("Authentication went wrong")
            val wishListResponse = getUserWishlistUseCase.invoke(userId)
            call.respondSuccess(data = wishListResponse)
        }

        put("/user") {
            val userRequest = call.receive<UserRequest>()
            val userId = call.principal<UserIdPrincipal>()?.name ?: throw AuthenticationException("Authentication went wrong")
            val user = getUserUseCase.invoke(userId)
            val updatedUser = user.copy(postedOffers = userRequest.postedOffers, wishlist = userRequest.wishlist)
            val success = updateUserUseCase.invoke(updatedUser)
            call.respondSuccess(data = success)
        }
    }

    post("/user") {
        val userInfo = call.receive<UserRequest>()
        val user = saveUserUseCase.invoke(userInfo)
        call.respondSuccess(code = HttpStatusCode.Created, data = user)
    }
}


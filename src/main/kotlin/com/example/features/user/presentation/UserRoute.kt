package com.example.features.user.presentation

import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.models.BasicUserDTO
import com.example.features.user.presentation.models.DetailedUserDTO
import com.example.features.user.presentation.models.WishlistDTO
import com.example.utils.getUserId
import com.example.utils.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    getUserOffersUseCase: GetUserOffersUseCase,
    getUserInfoUseCase: GetUserInfoUseCase,
    userWishlistUseCase: UserWishlistUseCase,
    saveUserUseCase: SaveUserUseCase,
    updateUserDataUseCase: UpdateUserDataUseCase
) {

    route("/user") {
        get("/{userId}") {
            val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
            val user: BasicUserDTO = getUserInfoUseCase.getBasicInfo(userId)
            call.respondSuccess(data = user)
        }

        // W ROUTE SPRAWDZASZ CZY COS NIE JEST NULLEM I CZY MA POPRAWNY FORMART, W USE CASE CZY DAME SA PRAWIDLOWE i robisz tam transformacje
        get("/{userId}/offers") {
            val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
            val offersResponse = getUserOffersUseCase.invoke(userId)
            call.respondSuccess(data = offersResponse)
        }

        authenticate("auth-bearer") {
            get("/my_info") {
                val userId = call.getUserId()
                val user: DetailedUserDTO = getUserInfoUseCase.getDetailedInfo(userId)
                call.respondSuccess(data = user)
            }

            get("/wishlist") {
                val userId = call.getUserId()
                val wishListResponse = userWishlistUseCase.get(userId)
                call.respondSuccess(data = wishListResponse)
            }

            put("/wishlist") {
                val wishlistDTO = call.receive<WishlistDTO>()
                val userId = call.getUserId()
                val user = userWishlistUseCase.put(userId, wishlistDTO)
                call.respondSuccess(data = user)
            }

            put("/offers") {
                val userId = call.getUserId()
                val postedOffersDTO = getUserOffersUseCase.invoke(userId)
                call.respondSuccess(data = postedOffersDTO)
            }

        }
    }

}


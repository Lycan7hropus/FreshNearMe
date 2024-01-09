package com.example.features.user.presentation

import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.models.BasicUserDto
import com.example.features.user.presentation.models.DetailedUserDto
import com.example.features.user.presentation.models.WishlistDto
import com.example.utils.extensionFunctions.getUserId
import com.example.utils.extensionFunctions.respondSuccess
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
            val user: BasicUserDto = getUserInfoUseCase.getBasicInfo(userId)
            call.respondSuccess(data = user)
        }

        // W ROUTE SPRAWDZASZ CZY COS NIE JEST NULLEM I CZY MA POPRAWNY FORMART, W USE CASE CZY DAME SA PRAWIDLOWE POD KĄTEM BIZNESOWYM i robisz tam transformacje
        get("/{userId}/offers") {
            val userId = call.parameters["userId"] ?: throw MissingRequestParameterException("userId")
            val offersResponse = getUserOffersUseCase.invoke(userId)
            call.respondSuccess(data = offersResponse)
        }

        authenticate("auth-jwt") {
            get("/my_info") {
                val userId = call.getUserId()
                val user: DetailedUserDto = getUserInfoUseCase.getDetailedInfo(userId)
                call.respondSuccess(data = user)
            }

            get("/wishlist") {
                val userId = call.getUserId()
                val wishListResponse = userWishlistUseCase.get(userId)
                call.respondSuccess(data = wishListResponse)
            }

            put("/wishlist") {
                val wishlistDTO = call.receive<WishlistDto>()
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


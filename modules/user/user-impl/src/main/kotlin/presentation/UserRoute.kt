package com.example.features.user.presentation

import com.example.features.user.domain.usecases.*
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import presentation.models.WishlistDto
import utils.extensionFunctions.getUserId
import utils.extensionFunctions.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

internal fun Route.userRoutes(
    getUserOffersUseCase: GetUserOffersUseCase = getKoin().get(),
    getUserInfoUseCase: GetUserInfoUseCase = getKoin().get(),
    userWishlistUseCase: UserWishlistUseCase = getKoin().get(),
    saveUserUseCase: SaveUserUseCase = getKoin().get(),
    updateUserDataUseCase: UpdateUserDataUseCase = getKoin().get(),
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


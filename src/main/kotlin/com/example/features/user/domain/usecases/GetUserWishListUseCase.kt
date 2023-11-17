package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.WishlistResponse
import com.example.utils.exceptions.UserNotFoundException

class GetUserWishListUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String): WishlistResponse {
        try {
            val offers = userRepository.getUserWishList(userId)
            return WishlistResponse(offers)
        } catch (e: UserNotFoundException) {
            // Log error and handle it appropriately
            throw e // or return a custom error response
        }
    }
}
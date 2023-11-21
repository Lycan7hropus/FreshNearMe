package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.WishlistResponse

class GetUserWishListUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String): WishlistResponse {
        val offers = userRepository.getUserWishList(userId)
        return WishlistResponse(offers)
    }
}
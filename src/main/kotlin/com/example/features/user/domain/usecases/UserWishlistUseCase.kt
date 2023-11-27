package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.WishlistDTO

class UserWishlistUseCase(private val userRepository: UserDataRepository) {
    suspend fun get(userId: String): WishlistDTO {
        val offers = userRepository.getUserWishList(userId)
        return WishlistDTO(offers)
    }

    suspend fun put(userId: String, wishlistDTO: WishlistDTO): WishlistDTO {
        val offers = userRepository.updateUserWishlist(userId, wishlistDTO.offers)
        return WishlistDTO(offers)
    }

}

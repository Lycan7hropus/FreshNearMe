package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.WishlistDto

class UserWishlistUseCase(private val userRepository: UserDataRepository) {
    suspend fun get(userId: String): WishlistDto {
        val offers = userRepository.getUserWishList(userId)
        return WishlistDto(offers)
    }

    suspend fun put(userId: String, wishlistDTO: WishlistDto): WishlistDto {
        val offers = userRepository.updateUserWishlist(userId, wishlistDTO.offers)
        return WishlistDto(offers)
    }

}

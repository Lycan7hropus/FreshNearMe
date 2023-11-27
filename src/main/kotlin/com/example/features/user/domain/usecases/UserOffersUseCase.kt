package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDTO
import com.example.features.user.presentation.models.WishlistDTO

class UserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun get(userId: String): PostedOffersDTO {
        val offers = userRepository.getUserWishList(userId)
        return PostedOffersDTO(offers)
    }

    suspend fun put(userId: String, wishlistDTO: WishlistDTO): PostedOffersDTO {
        //TODO
        val offers = userRepository.updateUserWishlist(userId, wishlistDTO.offers)
        return PostedOffersDTO(offers)
    }
}
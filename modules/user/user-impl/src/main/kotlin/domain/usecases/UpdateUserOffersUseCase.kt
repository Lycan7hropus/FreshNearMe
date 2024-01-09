package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDto
import com.example.features.user.presentation.models.WishlistDto

class UpdateUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String, wishlistDTO: WishlistDto): PostedOffersDto {
        val offers = userRepository.updateUserOffers(userId, wishlistDTO.offers)
        return PostedOffersDto(offers)
    }
}
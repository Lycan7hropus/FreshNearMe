package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDTO
import com.example.features.user.presentation.models.WishlistDTO

class UpdateUserOffersUseCase(private val userRepository: UserDataRepository) {

    suspend fun invoke(userId: String, wishlistDTO: WishlistDTO): PostedOffersDTO {
        val offers = userRepository.updateUserOffers(userId, wishlistDTO.offers)
        return PostedOffersDTO(offers)
    }
}
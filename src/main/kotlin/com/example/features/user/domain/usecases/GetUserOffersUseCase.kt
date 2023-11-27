package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDTO

class GetUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String): PostedOffersDTO {
        val offers = userRepository.getUserOffers(userId)
        return PostedOffersDTO(offers)
    }
}
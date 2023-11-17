package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.OffersResponse

class GetUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String): OffersResponse {
        val offers =  userRepository.getUserOffers(userId)
        return OffersResponse(offers)
    }
}
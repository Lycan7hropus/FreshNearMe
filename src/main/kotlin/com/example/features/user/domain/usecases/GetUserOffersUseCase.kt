package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDto

class GetUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend operator fun invoke(userId: String): PostedOffersDto {
        val offers = userRepository.getUserOffers(userId)
        return PostedOffersDto(offers)
    }
}
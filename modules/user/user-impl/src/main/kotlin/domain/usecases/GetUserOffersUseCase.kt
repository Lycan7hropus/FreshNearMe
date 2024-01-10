package com.example.features.user.domain.usecases

import domain.UserDataRepository
import presentation.models.PostedOffersDto

internal class GetUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String): PostedOffersDto {
        val offers = userRepository.getUserOffers(userId)
        return PostedOffersDto(offers)
    }
}
package com.example.features.offer.domain

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.presentation.dto.OffersDto
import com.example.models.Offer

class GetAllOffersUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(): Result<OffersDto> {
        return try {
            val offers = offerRepository.getOffers()
            val offersDto = OffersDto(offers)
            Result.success(offersDto)
        } catch (e: Exception) {
            // General exception handling if needed
            Result.failure(e)
        }
    }
}
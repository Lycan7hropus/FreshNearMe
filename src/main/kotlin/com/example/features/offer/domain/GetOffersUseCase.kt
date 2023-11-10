package com.example.features.offer.domain

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.presentation.dto.OffersDto
import com.example.models.Coordinates
import com.mongodb.client.model.geojson.Point

class GetOffersUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(
        category: kotlin.String? = null,
        distance: kotlin.Double? = null,
        coordinates: Coordinates?
    ): Result<OffersDto> {
        return try {
            val offers = offerRepository.getOffers(category, distance, coordinates)
            val offersDto = OffersDto(offers)
            Result.success(offersDto)
        } catch (e: Exception) {
            // General exception handling if needed
            Result.failure(e)
        }
    }
}
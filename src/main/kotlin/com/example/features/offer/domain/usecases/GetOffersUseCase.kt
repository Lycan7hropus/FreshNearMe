package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OffersDto
import com.example.models.Coordinates
import com.example.utils.CategoryACL

class GetOffersUseCase(private val offerRepository: OfferRepository, private val categoryACL: CategoryACL) {
    suspend operator fun invoke(
        categoryId: kotlin.String? = null,
        distance: kotlin.Double? = null,
        coordinates: Coordinates?  = null
    ): Result<OffersDto> {
        return try {
            val category = categoryId?.let { categoryACL.getCategoryById(it) }

            val offers = offerRepository.getOffers(category, distance, coordinates)
            val offersDto = OffersDto(offers)
            Result.success(offersDto)
        } catch (e: Exception) {
            // General exception handling if needed
            Result.failure(e)
        }
    }
}
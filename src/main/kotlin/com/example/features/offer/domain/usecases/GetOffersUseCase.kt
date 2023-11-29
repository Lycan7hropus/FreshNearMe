package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.CategoryService
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OffersDto
import com.example.models.Coordinates

class GetOffersUseCase(private val offerRepository: OfferRepository, private val categoryService: CategoryService) {
    suspend operator fun invoke(
        categoryId: kotlin.String? = null,
        distance: kotlin.Double? = null,
        coordinates: Coordinates? = null
    ): OffersDto {
        val category = categoryId?.let { categoryService.getCategoryById(it) }
        val offers = offerRepository.getOffers(category, distance, coordinates)

        return OffersDto(offers)
    }
}
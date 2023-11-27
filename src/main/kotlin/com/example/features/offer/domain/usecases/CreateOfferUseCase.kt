package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.CategoryProvider
import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
import com.example.utils.OfferCreationException

class CreateOfferUseCase(private val offerRepository: OfferRepository, private val categoryProvider: CategoryProvider) {
    suspend operator fun invoke(offerDto: OfferDto): Result<Offer> {
        return try {
            val category = categoryProvider.getCategoryById(offerDto.categoryId)
                ?: throw IllegalArgumentException("Niepoprawna kategoria")

            val offer = offerDto.toDomainModel(category)
            val createdOffer = offerRepository.saveOffer(offer)

            if (createdOffer != null) {
                Result.success(createdOffer)
            } else {
                Result.failure(OfferCreationException("Offer could not be created"))
            }
        } catch (e: IllegalArgumentException) {
            // This catch block will handle validation exceptions thrown by the Offer class
            Result.failure(e)
        }
    }
}


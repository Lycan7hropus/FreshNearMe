package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.CategoryProvider
import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto

class UpdateOfferUseCase(private val offerRepository: OfferRepository, private val categoryProvider: CategoryProvider) {
    suspend operator fun invoke(offerId: String, offerDto: OfferDto): Result<Offer> {
        return try {
            val category = categoryProvider.getCategoryById(offerDto.categoryId)
                ?: throw IllegalArgumentException("Niepoprawna kategoria")

            val offer = offerDto.toDomainModel(category).copy(id = offerId)
            val result = offerRepository.updateOffer(offer)
            if (result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception("Unable to update offer"))
            }
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }
}
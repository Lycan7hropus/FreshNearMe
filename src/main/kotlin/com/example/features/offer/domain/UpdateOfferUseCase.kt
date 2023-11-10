package com.example.features.offer.domain

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
import com.example.models.Offer

class UpdateOfferUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(offerId: String, offerDto: OfferDto): Result<Offer> {
        return try {
            val offer =  offerDto.toDomainModel().copy(id = offerId)
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
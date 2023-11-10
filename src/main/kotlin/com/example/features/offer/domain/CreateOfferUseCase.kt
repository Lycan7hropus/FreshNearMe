package com.example.features.offer.domain

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
import com.example.models.Offer
import com.example.utils.exceptions.OfferCreationException
import com.example.utils.exceptions.ValidationException

class CreateOfferUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(offerDto: OfferDto): Result<Offer> {
        return try {
            val offer = offerDto.toDomainModel()
            val createdOffer = offerRepository.createOffer(offer)

            if (createdOffer != null) {
                Result.success(createdOffer)
            } else {
                Result.failure(OfferCreationException("Offer could not be created"))
            }
        } catch (e: IllegalArgumentException ) {
            // This catch block will handle validation exceptions thrown by the Offer class
            Result.failure(e)
        }
    }
}


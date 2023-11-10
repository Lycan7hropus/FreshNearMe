package com.example.features.offer.domain

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
import com.example.models.Offer
import io.ktor.server.plugins.*

class GetOfferByIdUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(id: String): Result<OfferDto> {
        return try {
            val offer = offerRepository.getOfferById(id)
            if (offer != null) {
                Result.success(OfferDto(offer))
            } else {
                Result.failure(NotFoundException("Offer with id $id not found"))
            }
        } catch (e: Exception) {
            // General exception handling
            Result.failure(e)
        }
    }

}
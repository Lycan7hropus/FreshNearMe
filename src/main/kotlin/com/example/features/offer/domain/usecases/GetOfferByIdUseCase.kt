package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
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
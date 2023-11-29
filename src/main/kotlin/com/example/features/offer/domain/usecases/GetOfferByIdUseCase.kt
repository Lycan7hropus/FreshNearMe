package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto
import io.ktor.server.plugins.*

class GetOfferByIdUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(id: String): OfferDto{
        val offer = offerRepository.getOfferById(id)
        return OfferDto(offer)
    }
}
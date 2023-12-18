package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository

class GetOffersByNameUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(query: String): List<Offer> {
        return  offerRepository.findOffersWithQuery(query)
    }
}


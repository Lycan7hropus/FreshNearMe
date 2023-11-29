package com.example.features.offer.domain

import com.example.features.category.domain.Category
import com.example.models.Coordinates

interface OfferRepository {
    suspend fun getOffers(category: Category?, distance: Double?, coordinates: Coordinates?): List<Offer>
    suspend fun getOfferById(offerId: String): Offer
    suspend fun saveOffer(offer: Offer): Offer
    suspend fun updateOffer(offer: Offer): Offer
    suspend fun deleteOffer(offerId: String): Boolean
}
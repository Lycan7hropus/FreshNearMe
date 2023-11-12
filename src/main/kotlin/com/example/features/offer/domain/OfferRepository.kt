package com.example.features.offer.domain

import com.example.models.Category
import com.example.models.Coordinates
import com.example.models.Offer
import com.mongodb.client.model.geojson.Point

interface OfferRepository {
    suspend fun getOffers(category: Category?, distance: Double?, coordinates: Coordinates?): List<Offer>
    suspend fun getOfferById(offerId: String): Offer?
    suspend fun createOffer(offer: Offer): Offer?
    suspend fun updateOffer(offer: Offer): Offer?
}
package com.example.features.offer.data

import com.example.models.Coordinates
import com.example.models.Offer
import com.mongodb.client.model.geojson.Point

interface OfferRepository {
    suspend fun getOffers(category: String?, distance: Double?, coordinates: Coordinates?): List<Offer>
    suspend fun getOfferById(offerId: String): Offer?
    suspend fun createOffer(offer: Offer): Offer?
    suspend fun updateOffer(offer: Offer): Offer?
}
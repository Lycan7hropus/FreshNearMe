package com.example.features.offer.data

import com.example.models.Offer

interface OfferRepository {
    suspend fun getOffers(): List<Offer>
    suspend fun getOfferById(offerId: String): Offer?
    suspend fun createOffer(offer: Offer): Offer?
    suspend fun updateOffer(offer: Offer): Offer?
}
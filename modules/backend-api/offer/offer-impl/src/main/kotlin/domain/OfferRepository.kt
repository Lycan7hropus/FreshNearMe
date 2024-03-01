package domain

import dto.CategoryApiDto
import utils.models.Coordinates


internal interface OfferRepository {
    suspend fun getOffers(category: CategoryApiDto?, distance: Double?, coordinates: Coordinates?, minPrice: Int?, maxPrice: Int?): List<Offer>
    suspend fun getOfferById(offerId: String): Offer
    suspend fun saveOffer(offer: Offer): Offer
    suspend fun updateOffer(offer: Offer): Offer
    suspend fun deleteOffer(offerId: String): Boolean
    suspend fun findOffersWithQuery(query: String): List<Offer>
}
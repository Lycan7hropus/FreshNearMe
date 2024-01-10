package domain

import CategoryApiDto
import com.example.utils.models.Coordinates


internal interface OfferRepository {
    suspend fun getOffers(category: CategoryApiDto?, distance: Double?, coordinates: Coordinates?): List<Offer>
    suspend fun getOfferById(offerId: String): Offer
    suspend fun saveOffer(offer: Offer): Offer
    suspend fun updateOffer(offer: Offer): Offer
    suspend fun deleteOffer(offerId: String): Boolean
    suspend fun findOffersWithQuery(query: String): List<Offer>
}
package application

import presentation.dto.OfferDto
import presentation.dto.OffersDto
import utils.models.Coordinates

interface OfferService {
    suspend fun createOffer(offerDto: OfferDto, sellerId: String): OfferDto
    suspend fun getOfferById(id: String): OfferDto
    suspend fun getOffersByName(query: String): List<OfferDto>
    suspend fun getOffers(categoryId: String?, distance: Double?, coordinates: Coordinates?): OffersDto
    suspend fun updateOffer(offerId: String, userId: String, offerDto: OfferDto): OfferDto
}
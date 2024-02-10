package application

import CategoryApi
import domain.Offer
import domain.OfferRepository
import presentation.dto.OfferDto
import presentation.dto.OffersDto
import utils.ResourceAccessDenied
import utils.models.Coordinates

internal class OfferServiceImpl(
    private val offerRepository: OfferRepository,
//    private val categoryApi: CategoryApi
) : OfferService {

    override suspend fun createOffer(offerDto: OfferDto, sellerId: String): OfferDto {
//        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto as Offer//TODO
        return offerRepository.saveOffer(offer) as OfferDto//TODO
    }

    override suspend fun getOfferById(id: String): OfferDto {
        val offer = offerRepository.getOfferById(id)
        return offer as OfferDto//TODO
    }

    override suspend fun getOffersByName(query: String): List<OfferDto> {
        return offerRepository.findOffersWithQuery(query) as List<OfferDto>//TODO
    }

    override suspend fun getOffers(categoryId: String?, distance: Double?, coordinates: Coordinates?): OffersDto {
//        val category = categoryId?.let { categoryApi.getCategoryById(it) }
        val offers = offerRepository.getOffers(null, distance, coordinates)
        return offers as OffersDto//TODO
    }

    override suspend fun updateOffer(offerId: String, userId: String, offerDto: OfferDto): OfferDto {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if (sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
//        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto as Offer//TODO
        return offerRepository.updateOffer(offer) as OfferDto //TODO
    }
}
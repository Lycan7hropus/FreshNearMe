package application

import CategoryApi
import domain.Offer
import domain.OfferRepository
import presentation.dto.OfferDto
import presentation.dto.OffersDto
import utils.ResourceAccessDenied
import utils.models.Coordinates
import utils.transform

internal class OfferServiceImpl(
    private val offerRepository: OfferRepository,
    private val categoryApi: CategoryApi
) : OfferService {

    override suspend fun createOffer(offerDto: OfferDto, sellerId: String): OfferDto {
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto.transform<Offer>()
        return offerRepository.saveOffer(offer).transform()
    }

    override suspend fun getOfferById(id: String): OfferDto {
        val offer = offerRepository.getOfferById(id)
        return offer.transform()
    }

    override suspend fun getOffersByName(query: String): List<OfferDto> {
        return offerRepository.findOffersWithQuery(query).transform()
    }

    override suspend fun getOffers(categoryId: String?, distance: Double?, coordinates: Coordinates?): OffersDto {
        val category = categoryId?.let { categoryApi.getCategoryById(it) }
        val offers = offerRepository.getOffers(category, distance, coordinates)
        return offers.transform()
    }

    override suspend fun updateOffer(offerId: String, userId: String, offerDto: OfferDto): OfferDto {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if (sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto.transform<Offer>()
        return offerRepository.updateOffer(offer).transform()
    }
}
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
    private val categoryApi: CategoryApi
) : OfferService {

    override suspend fun createOffer(offerDto: OfferDto, sellerId: String): Offer {
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto.toDomainModel(category, sellerId)
        return offerRepository.saveOffer(offer)
    }

    override suspend fun getOfferById(id: String): OfferDto {
        val offer = offerRepository.getOfferById(id)
        return OfferDto(offer)
    }

    override suspend fun getOffersByName(query: String): List<Offer> {
        return offerRepository.findOffersWithQuery(query)
    }

    override suspend fun getOffers(categoryId: String?, distance: Double?, coordinates: Coordinates?): OffersDto {
        val category = categoryId?.let { categoryApi.getCategoryById(it) }
        val offers = offerRepository.getOffers(category, distance, coordinates)
        return OffersDto(offers)
    }

    override suspend fun updateOffer(offerId: String, userId: String, offerDto: OfferDto): Offer {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if (sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto.toDomainModel(category, sellerId).copy(id = offerId)
        return offerRepository.updateOffer(offer)
    }
}
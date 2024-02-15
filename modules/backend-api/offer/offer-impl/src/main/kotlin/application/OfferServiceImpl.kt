package application

import CategoryApi
import infrastructure.toDto
import domain.OfferFactory
import domain.OfferRepository
import presentation.dto.OfferDto
import presentation.dto.OffersDto
import utils.ResourceAccessDenied
import utils.models.Coordinates

internal class OfferServiceImpl(
    private val offerRepository: OfferRepository,
    private val categoryApi: CategoryApi,
    private val offerFactory: OfferFactory
) : OfferService {

    override suspend fun createOffer(offerDto: OfferDto, sellerId: String): OfferDto {
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerFactory.createOfferFromDTO(offerDto, sellerId)
        return offerRepository.saveOffer(offer).toDto()
    }

    override suspend fun getOfferById(id: String): OfferDto {
        val offer = offerRepository.getOfferById(id)
        return offer.toDto()
    }

    override suspend fun getOffersByName(query: String): List<OfferDto> {
        return offerRepository.findOffersWithQuery(query).map {
            it.toDto()
        }
    }

    override suspend fun getOffers(categoryId: String?, distance: Double?, coordinates: Coordinates?): OffersDto {
        val category = categoryId?.let { categoryApi.getCategoryById(it) }
        val offers = offerRepository.getOffers(category, distance, coordinates)
        return OffersDto(offers.map { it.toDto() })
    }

    override suspend fun updateOffer(offerId: String, userId: String, offerDto: OfferDto): OfferDto {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if (sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerFactory.createOfferFromDTO(offerDto,userId)
        return offerRepository.updateOffer(offer).toDto()
    }
}
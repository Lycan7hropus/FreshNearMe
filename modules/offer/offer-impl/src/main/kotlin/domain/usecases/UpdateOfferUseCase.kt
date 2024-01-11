package domain.usecases


import CategoryApi
import presentation.dto.OfferDto
import utils.ResourceAccessDenied
import domain.Offer
import domain.OfferRepository

internal class UpdateOfferUseCase(private val offerRepository: OfferRepository, private val categoryApi: CategoryApi) {
    suspend operator fun invoke(offerId: String, userId : String, offerDto: OfferDto): Offer {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if(sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
        val category = categoryApi.getCategoryById(offerDto.categoryId)
        val offer = offerDto.toDomainModel(category, sellerId).copy(id = offerId)

        return offerRepository.updateOffer(offer)
    }
}
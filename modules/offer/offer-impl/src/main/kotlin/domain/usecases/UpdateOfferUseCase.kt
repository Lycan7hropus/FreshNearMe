package domain.usecases

import com.example.features.offer.domain.CategoryService
import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository
import presentation.dto.OfferDto
import com.example.utils.ResourceAccessDenied

class UpdateOfferUseCase(private val offerRepository: OfferRepository, private val categoryService: CategoryService) {
    suspend operator fun invoke(offerId: String, userId : String, offerDto: OfferDto): Offer {
        val sellerId = offerRepository.getOfferById(offerId).sellerId
        if(sellerId != userId) throw ResourceAccessDenied("You cannot edit this offer")
        val category = categoryService.getCategoryById(offerDto.categoryId)
        val offer = offerDto.toDomainModel(category, sellerId).copy(id = offerId)

        return offerRepository.updateOffer(offer)
    }
}
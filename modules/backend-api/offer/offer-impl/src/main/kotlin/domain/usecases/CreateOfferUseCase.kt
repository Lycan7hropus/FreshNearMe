package domain.usecases

import CategoryApi
import domain.Offer
import domain.OfferRepository
import presentation.dto.OfferDto

internal class CreateOfferUseCase(private val offerRepository: OfferRepository, private val categoryApi: CategoryApi) {
    suspend operator fun invoke(offerDto: OfferDto, sellerId: String): Offer {
        val category = categoryApi.getCategoryById(offerDto.categoryId)

        val offer = offerDto.toDomainModel(category, sellerId)

        return offerRepository.saveOffer(offer)
    }
}


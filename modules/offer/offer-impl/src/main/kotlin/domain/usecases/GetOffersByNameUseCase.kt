package domain.usecases

import domain.Offer
import domain.OfferRepository

internal class GetOffersByNameUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(query: String): List<Offer> {
        return  offerRepository.findOffersWithQuery(query)
    }
}


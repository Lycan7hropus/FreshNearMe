package domain.usecases


import domain.OfferRepository
import presentation.dto.OfferDto

internal class GetOfferByIdUseCase(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(id: String): OfferDto {
        val offer = offerRepository.getOfferById(id)
        return OfferDto(offer)
    }
}
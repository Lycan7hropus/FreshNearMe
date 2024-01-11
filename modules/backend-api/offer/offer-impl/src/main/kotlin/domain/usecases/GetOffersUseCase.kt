package domain.usecases


import CategoryApi
import utils.models.Coordinates
import domain.OfferRepository
import presentation.dto.OffersDto

internal class GetOffersUseCase(private val offerRepository: OfferRepository, private val categoryApi: CategoryApi) {
    suspend operator fun invoke(
        categoryId: kotlin.String? = null,
        distance: kotlin.Double? = null,
        coordinates: Coordinates? = null
    ): OffersDto {
        val category = categoryId?.let { categoryApi.getCategoryById(it) }
        val offers = offerRepository.getOffers(category, distance, coordinates)

        return OffersDto(offers)
    }
}
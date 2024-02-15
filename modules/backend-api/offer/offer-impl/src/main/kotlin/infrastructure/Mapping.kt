package infrastructure

import infrastructure.mappers.OfferDtoConverter
import domain.Offer
import presentation.dto.OfferDto
internal fun Offer.toDto(): OfferDto {
    return OfferDtoConverter.INSTANCE.convertToDto(this)
}
@Deprecated("Functionality not finished")
internal fun OfferDto.toModel(): Offer {
    // TODO: This functionality is not yet implemented. Do not use it.
    return OfferDtoConverter.INSTANCE.convertToModel(this)
}

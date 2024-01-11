package presentation.dto

import domain.Offer
import kotlinx.serialization.Serializable

@Serializable
internal data class OffersDto(val offers: List<Offer> = listOf())
package presentation.dto

import com.example.features.offer.domain.Offer
import kotlinx.serialization.Serializable

@Serializable
class OffersDto(val offers: List<Offer> = listOf())
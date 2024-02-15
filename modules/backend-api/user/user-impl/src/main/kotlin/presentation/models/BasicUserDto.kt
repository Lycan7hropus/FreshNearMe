package presentation.models

import com.example.OfferApiDto
import kotlinx.serialization.SerialName

internal data class
BasicUserDto(
    val id: String,
    @SerialName("posted_offers") val postedOffers: List<OfferApiDto>,
    @SerialName("given_name") val givenName: String,
    val picture: String,
)



package presentation.models

import com.example.OfferApiDto
import kotlinx.serialization.SerialName

internal data class DetailedUserDto(
    val id: String,
    @SerialName("wish_list") val wishlist: List<OfferApiDto>,
    @SerialName("posted_offers") val postedOffers: List<OfferApiDto>,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String
)

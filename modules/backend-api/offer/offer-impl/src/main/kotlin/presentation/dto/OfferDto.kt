package presentation.dto


import dto.CategoryApiDto
import utils.models.Coordinates
import utils.models.GeoPoint
import domain.Offer
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import java.util.*


 data class OfferDto(
    val id: String?,
    val name: String,
    val categoryId: String,
    val price: Double,
    val phoneNumber: String,
    val description: String,
    val imageUrl: String?,
    val coordinates: Coordinates
)
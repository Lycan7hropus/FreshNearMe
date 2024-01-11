package presentation.dto


import CategoryApiDto
import utils.models.Coordinates
import utils.models.GeoPoint
import domain.Offer
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import java.util.*


internal data class OfferDto(
    val id: String?,
    val name: String,
    val categoryId: String,
    val price: Double,
    val phoneNumber: String,
    val description: String,
    val imageUrl: String?,
    val coordinates: Coordinates
) {
    constructor(offer: Offer) : this(
        id = offer.id,
        name = offer.name,
        categoryId = offer.category.id,
        price = offer.price,
        phoneNumber = offer.phoneNumber,
        description = offer.description,
        imageUrl = offer.imageUrl,
        coordinates = Coordinates(longitude = offer.geoPoint.coordinates[0], latitude = offer.geoPoint.coordinates[1])
    )

    fun toDomainModel(category: CategoryApiDto, sellerId: String): Offer {
        // Here we generate a random ID and use the current time for the postedTime.
        // Adjust according to your application's requirements.
        return Offer(
            id = id ?: UUID.randomUUID().toString(),
            name = name,
            category = category,
            price = price,
            phoneNumber = phoneNumber,
            description = description,
            imageUrl = imageUrl,
            postedTime = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()), // Or use the appropriate time zone
            sellerId = sellerId,
            geoPoint = GeoPoint(coordinates = listOf(coordinates.longitude, coordinates.latitude)),
            isActive = true // Assuming a new offer is always active initially
        )
    }
}


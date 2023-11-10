package com.example.features.offer.presentation.dto

import com.example.models.Offer
import com.example.models.SimpleLocation
import com.example.models.geolocation.GeoJsonPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
class OfferDto(
    var id: String?,
    var name: String,
    var category: String,
    var price: Double,
    var phoneNumber: String,
    var description: String,
    var imageUrl: String?, //BaseEncodedImage
    val sellerId: String,
    var simpleLocation: SimpleLocation
) {
    constructor(offer: Offer) : this(
        id = offer.id,
        name = offer.name,
        category = offer.category,
        price = offer.price,
        phoneNumber = offer.phoneNumber,
        description = offer.description,
        imageUrl = offer.imageUrl,
        sellerId = offer.sellerId,
        simpleLocation = SimpleLocation(offer.geoLocation.coordinates.get(0),offer.geoLocation.coordinates.get(1))
    )

    fun toDomainModel(): Offer {
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
            postedTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()), // Or use the appropriate time zone
            sellerId = sellerId,
            geoLocation = GeoJsonPoint(coordinates = listOf(simpleLocation.longitude, simpleLocation.latitude)),
            isActive = true // Assuming a new offer is always active initially
        )
    }
}
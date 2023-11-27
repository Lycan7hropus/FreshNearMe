package com.example.features.offer.presentation.dto

import com.example.features.category.domain.Category
import com.example.features.offer.domain.Offer
import com.example.models.Coordinates
import com.example.models.GeoPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class OfferDto(
    var id: String?,
    var name: String,
    var categoryId: String,
    var price: Double,
    var phoneNumber: String,
    var description: String,
    var imageUrl: String?,
    val sellerId: String,
    var coordinates: Coordinates
) {
    constructor(offer: Offer) : this(
        id = offer.id,
        name = offer.name,
        categoryId = offer.category.id,
        price = offer.price,
        phoneNumber = offer.phoneNumber,
        description = offer.description,
        imageUrl = offer.imageUrl,
        sellerId = offer.sellerId,
        coordinates = Coordinates(longitude = offer.geoPoint.coordinates[0], latitude = offer.geoPoint.coordinates[1])
    )

    fun toDomainModel(category: Category): Offer {
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


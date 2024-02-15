package domain

import CategoryApi
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import presentation.dto.OfferDto
import utils.models.GeoPoint
import java.util.*


internal class OfferFactory(private val categoryApi: CategoryApi) {
    suspend fun createOfferFromDTO(offerDto: OfferDto, sellerId: String): Offer {
        return Offer(
            id = offerDto.id ?: UUID.randomUUID().toString(),
            name = offerDto.name,
            category = categoryApi.getCategoryById(offerDto.categoryId),
            price = offerDto.price,
            phoneNumber = offerDto.phoneNumber,
            description = offerDto.description,
            imageUrl = offerDto.imageUrl,
            postedTime = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()), // Or use the appropriate time zone
            sellerId = sellerId,
            geoPoint = GeoPoint(coordinates = listOf(offerDto.coordinates.longitude, offerDto.coordinates.latitude)),
            isActive = true // Assuming a new offer is always active initially
        )
    }
}

package domain


import dto.CategoryApiDto
import utils.models.GeoPoint
import kotlinx.datetime.LocalDateTime

import org.bson.codecs.pojo.annotations.BsonId


internal data class Offer(
    @BsonId
    val id: String,
    var name: String,
    var category: CategoryApiDto,
    var price: Double,
    var phoneNumber: String,
    var description: String,
    var imageUrl: String?,
    val postedTime: LocalDateTime,
    val sellerId: String,
    val geoPoint: GeoPoint,
    var isActive: Boolean = true
) {
    init {
        require(name.isNotBlank()) { "Offer name cannot be blank." }
        require(price >= 0) { "Price cannot be negative." }
        require(phoneNumber.isNotBlank()) { "Phone number cannot be blank." }
        require(description.isNotBlank()) { "Description cannot be blank." }
        require(sellerId.isNotBlank()) { "Seller ID cannot be blank." }
        // Add more validations as needed
    }
}

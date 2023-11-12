package com.example.models
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Offer(
    @BsonId
    val id: String,
    var name: String,
    var category: Category,
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
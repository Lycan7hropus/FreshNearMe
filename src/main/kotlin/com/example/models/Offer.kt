package com.example.models
import java.time.LocalDateTime


data class Offer(
    val id: String,
    var name: String,
    var category: Category,
    var price: Double,
    var phoneNumber: String,
    var description: String,
    var imageUrl: String?,
    val postedTime: LocalDateTime,
    val sellerId: String,
    var simpleLocation: SimpleLocation,
    var isActive: Boolean = true
) {
    fun deactivate() {
        isActive = false
    }

    fun updatePrice(newPrice: Double) {
        if (newPrice >= 0) {
            price = newPrice
        } else {
            throw IllegalArgumentException("Price cannot be negative.")
        }
    }

}
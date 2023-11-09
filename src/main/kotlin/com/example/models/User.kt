package com.example.models

data class User(
    val id: String,
    val username: String,
    val email: String,
    val address: SimpleLocation,
    val passwordHash: String,
    val contactInfo: String,
    val postedOffers: List<String>,
    val wishlist: List<String>
)
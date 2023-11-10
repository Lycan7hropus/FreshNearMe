package com.example.models

data class User(
    val id: String,
    val username: String,
    val email: String,
    val address: Coordinates,
    val passwordHash: String,
    val contactInfo: String,
    val postedOffers: List<String>,
    val wishlist: List<String>
)
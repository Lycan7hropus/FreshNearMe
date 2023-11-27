package com.example.features.user.presentation.models
import com.example.models.Offer

data class UserRequest (
    val wishlist: List<Offer>,
    val postedOffers: List<Offer>
)
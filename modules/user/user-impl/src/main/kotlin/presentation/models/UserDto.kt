package com.example.features.user.presentation.models

import com.example.features.offer.domain.Offer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicUserDto(
    val id: String,
    @SerialName("posted_offers") val postedOffers: List<Offer>,
    @SerialName("given_name") val givenName: String,
    val picture: String,
)

@Serializable
data class DetailedUserDto(
    val id: String,
    @SerialName("wish_list") val wishlist: List<Offer>,
    @SerialName("posted_offers") val postedOffers: List<Offer>,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String
)

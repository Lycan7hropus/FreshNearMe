package com.example.features.authentication.domain.model

import kotlinx.serialization.SerialName

data class UserGoogleInfo(
    @SerialName("google_id") val id: String,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String
)
package com.example.features.authentication.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GoogleTokenInfo(
    @SerialName("issued_to") val issuedTo: String,
    @SerialName("audience") val audience: String,
    @SerialName("user_id") val userId: String,
    @SerialName("scope") val scope: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("access_type") val accessType: String
)


@Serializable
data class GoogleTokenError(
    val error: String,
    @SerialName("error_description") val errorDescription: String
)

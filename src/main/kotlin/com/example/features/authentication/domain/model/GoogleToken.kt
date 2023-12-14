package com.example.features.authentication.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class GoogleTokenInfo(
    @SerialName("issued_to") val issuedTo: String,
    @SerialName("audience") val audience: String,
    @SerialName("user_id") val userId: String,
    @SerialName("scope") val scope: String,
    @SerialName("expires_in") val expiresIn: Int,
    val email: String?,
    val verified_email: Boolean?,
    @SerialName("access_type") val accessType: String
)


@Serializable
public data class GoogleTokenError(
    val error: String,
    @SerialName("error_description") val errorDescription: String
)

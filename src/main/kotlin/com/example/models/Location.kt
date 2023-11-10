package com.example.models

import kotlinx.serialization.Serializable
@Serializable
data class DetailedLocation(
    val plusCode: PlusCode,
    val addressComponents: List<AddressComponent>,
    val formattedAddress: String,
    val geometry: Geometry,
    val placeId: String,
    val types: List<String>
)
@Serializable
data class PlusCode(
    val compoundCode: String,
    val globalCode: String
)
@Serializable
data class AddressComponent(
    val longName: String,
    val shortName: String,
    val types: List<String>
)
@Serializable
data class Geometry(
    val location: SimpleLocation,
    val locationType: String,
    val viewport: Viewport
)
@Serializable
data class Viewport(
    val northeast: SimpleLocation,
    val southwest: SimpleLocation
)
@Serializable
data class SimpleLocation(
    val longitude: Double,
    val latitude: Double
)
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
    val location: Coordinates,
    val locationType: String,
    val viewport: Viewport
)

@Serializable
data class Viewport(
    val northeast: Coordinates,
    val southwest: Coordinates
)

@Serializable
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class GeoPoint(
    val type: String = "Point",
    val coordinates: List<Double>
)
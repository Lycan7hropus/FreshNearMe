package com.example.models

data class DetailedLocation(
    val plusCode: PlusCode,
    val addressComponents: List<AddressComponent>,
    val formattedAddress: String,
    val geometry: Geometry,
    val placeId: String,
    val types: List<String>
)

data class PlusCode(
    val compoundCode: String,
    val globalCode: String
)

data class AddressComponent(
    val longName: String,
    val shortName: String,
    val types: List<String>
)

data class Geometry(
    val location: SimpleLocation,
    val locationType: String,
    val viewport: Viewport
)

data class Viewport(
    val northeast: SimpleLocation,
    val southwest: SimpleLocation
)

data class SimpleLocation(
    val latitude: Double,
    val longitude: Double
)

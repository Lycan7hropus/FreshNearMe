package com.example.models

import kotlinx.serialization.Serializable


@Serializable
public data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

@Serializable
public data class GeoPoint(
    val type: String = "Point",
    val coordinates: List<Double>
)
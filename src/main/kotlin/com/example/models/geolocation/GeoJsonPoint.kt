package com.example.models.geolocation

import kotlinx.serialization.Serializable

@Serializable
data class GeoJsonPoint(
    private val type: String = "Point",  // Always "Point" for GeoJSON points
    val coordinates: List<Double> // List of [longitude, latitude]
)
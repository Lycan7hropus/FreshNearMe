package utils.models

import kotlinx.serialization.Serializable
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
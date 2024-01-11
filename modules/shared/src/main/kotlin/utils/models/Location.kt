package utils.models



data class Coordinates(
    val latitude: Double,
    val longitude: Double
)


data class GeoPoint(
    val type: String = "Point",
    val coordinates: List<Double>
)
package com.example.services

import com.example.models.GeoPoint
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class GeoPointService {
    fun randomGeoPointNearWarsaw(radiusKm: Double = 1000.0): GeoPoint {
        val warsawLat = 52.2297
        val warsawLon = 21.0122
        val earthRadiusKm = 6371.0

        // Convert radius from kilometers to degrees
        val radiusInDegrees = radiusKm / earthRadiusKm

        // Generate a random distance and angle
        val u = Random.nextDouble()
        val v = Random.nextDouble()
        val w = radiusInDegrees * sqrt(u)
        val t = 2 * PI * v
        val x = w * cos(t)
        val y = w * sin(t)

        // Adjust the x-coordinate for the shrinking of the east-west distances
        val newX = x / cos(Math.toRadians(warsawLat))

        val foundLongitude = newX + warsawLon
        val foundLatitude = y + warsawLat

        return GeoPoint(coordinates = listOf(foundLatitude, foundLongitude))
    }
}
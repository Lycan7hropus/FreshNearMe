package com.example.utils.extensionFunctions

import com.example.models.Coordinates

fun String.toCoordinates(): Coordinates? {
    val parts = this.split(',')
    if (parts.size == 2) {
        val latitude = parts[0].toDoubleOrNull()
        val longitude = parts[1].toDoubleOrNull()
        if (latitude != null && longitude != null) {
            return Coordinates(latitude, longitude)
        }
    }
    return null
}

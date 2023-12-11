package com.example.services

import kotlinx.datetime.LocalDateTime
import kotlin.random.Random

class LocalDateTimeService {
    fun randomLocalDateTime(
        startYear: Int = 2022,
        endYear: Int = 2023
    ): LocalDateTime {
        val year = Random.nextInt(startYear, endYear + 1)
        val month = Random.nextInt(1, 13) // Month is 1-based
        val dayOfMonth = Random.nextInt(1, 29) // Simplified to handle up to 28 days
        val hour = Random.nextInt(24)
        val minute = Random.nextInt(60)
        val second = Random.nextInt(60)

        return LocalDateTime(year, month, dayOfMonth, hour, minute, second)
    }

    fun randomEpoch(startYear: Int  = 2020, endYear: Int  = 2024): Long {
        require(startYear <= endYear) { "Start value must be less than or equal to end value" }
        return Random.nextLong(yearToEpoch(startYear), yearToEpoch(endYear) + 1)
    }

    private fun yearToEpoch(year: Int): Long {
        return year * 31536000L // Przybliżona liczba sekund w roku (nie uwzględnia lat przestępnych)
    }
}
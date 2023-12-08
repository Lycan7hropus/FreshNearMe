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
}
package com.example.features.rating.presentation.models

import com.example.features.rating.domain.models.Rating

data class RatingDto(
    val ratedUserId: String,
    val score: Int,
    val comment: String?
) {
    fun toEntity(): Rating {
        TODO("Not yet implemented")
    }
}
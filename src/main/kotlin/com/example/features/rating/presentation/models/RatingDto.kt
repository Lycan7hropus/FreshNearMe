package com.example.features.rating.presentation.models

data class RatingDto(
    val ratedUserId: String,
    val score: Int,
    val comment: String?
)
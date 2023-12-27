package com.example.features.rating.domain.models

data class Rating(
    val ratedUserId: String,
    val ratingAuthorUserId: String,
    val score: Int,
    val comment: String?
)
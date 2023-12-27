package com.example.features.rating.domain

import com.example.features.rating.domain.models.Rating

interface RatingService {

    fun addRating(rating: Rating): Rating

    fun getRatingsForUser(userId: String): List<Rating>

    fun getRatingsForProduct(productId: String): List<Rating>

    fun calculateAverageRatingForUser(userId: String): Double

    fun calculateAverageRatingForProduct(productId: String): Double
    fun getGivenRatingsByUser(userId: String): Rating

}

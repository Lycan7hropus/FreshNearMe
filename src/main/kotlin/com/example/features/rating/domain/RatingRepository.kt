package com.example.features.rating.domain

import com.example.features.rating.domain.models.Rating

interface RatingRepository {
    suspend fun saveRating(rating: Rating): Rating

    suspend fun getRatingsByRatedUserId(userId: String): List<Rating>

    suspend fun getRatingByRatingUserId(userId: String): List<Rating>

    suspend fun getRatingById(ratingId: String): Rating
    suspend fun editRating(rating: Rating): Rating

    suspend fun removeRatingById(ratingId: String)

}
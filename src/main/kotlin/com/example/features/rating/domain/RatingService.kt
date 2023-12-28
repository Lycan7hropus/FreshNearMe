package com.example.features.rating.domain

import com.example.features.rating.domain.models.Rating
import com.example.features.rating.presentation.models.RatingDto

interface RatingService {

    suspend fun addRating(rating: RatingDto): Rating

    suspend fun getRatingsForUser(userId: String): List<Rating>

    suspend fun getGivenRatingsByUser(userId: String): List<Rating>
    suspend fun getRatingById(ratingId: String): Rating
    suspend fun editRating(rating: RatingDto): Rating
    suspend fun deleteRating(ratingId: String)
}

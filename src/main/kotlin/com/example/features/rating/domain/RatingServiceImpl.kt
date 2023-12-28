package com.example.features.rating.domain

import com.example.features.rating.domain.models.Rating
import com.example.features.rating.presentation.models.RatingDto

class RatingServiceImpl(private val ratingRepository: RatingRepository): RatingService {
    override suspend fun addRating(rating: RatingDto): Rating {
        return ratingRepository.saveRating(rating.toEntity())
    }

    override suspend fun getRatingsForUser(userId: String): List<Rating> {
        return ratingRepository.getRatingsByRatedUserId(userId)
    }

    override suspend fun getGivenRatingsByUser(userId: String): List<Rating> {
        return ratingRepository.getRatingByRatingUserId(userId)
    }

    override suspend fun getRatingById(ratingId: String): Rating {
        return ratingRepository.getRatingById(ratingId)
    }

    override suspend fun editRating(rating: RatingDto): Rating {
        return ratingRepository.editRating(rating.toEntity())
    }

    override suspend fun deleteRating(ratingId: String) {
        ratingRepository.removeRatingById(ratingId)
    }


}
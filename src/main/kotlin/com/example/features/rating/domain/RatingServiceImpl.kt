package com.example.features.rating.domain

import com.example.features.rating.domain.models.Rating

class RatingServiceImpl(ratingRepository: RatingRepository): RatingService {
    override fun addRating(rating: Rating): Rating {
        TODO("Not yet implemented")
    }

    override fun getRatingsForUser(userId: String): List<Rating> {
        TODO("Not yet implemented")
    }

    override fun getRatingsForProduct(productId: String): List<Rating> {
        TODO("Not yet implemented")
    }

    override fun calculateAverageRatingForUser(userId: String): Double {
        TODO("Not yet implemented")
    }

    override fun calculateAverageRatingForProduct(productId: String): Double {
        TODO("Not yet implemented")
    }

    override fun getGivenRatingsByUser(userId: String): Rating {
        TODO("Not yet implemented")
    }


}
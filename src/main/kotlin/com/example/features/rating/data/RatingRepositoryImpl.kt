package com.example.features.rating.data

import com.example.features.rating.domain.RatingRepository
import com.example.features.rating.domain.models.Rating
import com.example.utils.ResourceNotFound
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class RatingRepositoryImpl(private val ratingCollection: CoroutineCollection<Rating>) : RatingRepository {

    override suspend fun saveRating(rating: Rating): Rating {
        ratingCollection.insertOne(rating)
        return rating
    }

    override suspend fun getRatingsByRatedUserId(userId: String): List<Rating> {
        return ratingCollection.find(Rating::ratedUserId eq userId).toList()
    }

    override suspend fun getRatingByRatingUserId(userId: String): List<Rating> {
        return ratingCollection.find(Rating::ratingUserId eq userId).toList()
    }

    override suspend fun getRatingById(ratingId: String): Rating {
        return ratingCollection.findOne(Rating::id eq ratingId) ?: throw ResourceNotFound("Rating with that id not found in database")
    }

    override suspend fun editRating(rating: Rating): Rating {
        val updateResult = ratingCollection.updateOne(Rating::id eq rating.id, rating)
        if (updateResult.matchedCount == 0.toLong()) {
            throw ResourceNotFound("Rating with id ${rating.id} not found")
        }
        return rating
    }

    override suspend fun removeRatingById(ratingId: String) {
        val deleteResult = ratingCollection.deleteOne(Rating::id eq ratingId)
        if (deleteResult.deletedCount.toInt() == 0) {
            throw ResourceNotFound("Rating with id $ratingId not found")
        }
    }
}


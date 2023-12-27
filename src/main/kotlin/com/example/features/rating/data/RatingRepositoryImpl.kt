package com.example.features.rating.data

import com.example.features.offer.domain.Offer
import com.example.features.rating.domain.RatingRepository
import com.example.features.rating.domain.models.Rating
import org.litote.kmongo.coroutine.CoroutineCollection

class RatingRepositoryImpl(private val ratingCollection: CoroutineCollection<Rating>) : RatingRepository {
}
package com.example.features.rating.domain.models

import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId

data class Rating(
    @BsonId
    val id: String,
    @SerialName("rated_user_id") val ratedUserId: String,
    @SerialName("rating_user_id") val ratingUserId: String,
    val score: Int,
    val comment: String?
)
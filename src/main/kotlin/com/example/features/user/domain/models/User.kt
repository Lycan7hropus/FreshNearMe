package com.example.features.user.domain.models


import com.example.features.offer.domain.Offer
import com.example.features.user.presentation.models.BasicUserDto
import com.example.features.user.presentation.models.DetailedUserDto
import com.example.utils.Role
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class User(
    @BsonId
    val id: String = UUID.randomUUID().toString(),
    val role: Role,
    @SerialName("wish_list") val wishlist: List<Offer>,
    @SerialName("posted_offers") val postedOffers: List<Offer>,
) {
    fun toBasicDTO(): BasicUserDto {
        TODO("Not yet implemented")
    }

    fun toDetailedDTO(): DetailedUserDto {
        TODO("Not yet implemented")
    }
}
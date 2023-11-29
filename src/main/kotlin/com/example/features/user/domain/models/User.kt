package com.example.features.user.domain.models

import com.example.features.authentication.domain.model.GoogleUserInfo
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
    val googleInfo: GoogleUserInfo
) {
    fun getUserWithUpdatedInfo(userInfo: GoogleUserInfo): User {
        return this.copy(googleInfo = googleInfo)
    }

    fun toBasicDTO(): BasicUserDto {
        return BasicUserDto(
            id = id,
            givenName = googleInfo.givenName,
            picture = googleInfo.picture,
            postedOffers = postedOffers
        )
    }

    fun toDetailedDTO(): DetailedUserDto {
        return DetailedUserDto(
            id = id,
            familyName = googleInfo.familyName,
            givenName = googleInfo.givenName,
            locale = googleInfo.locale,
            name = googleInfo.name,
            picture = googleInfo.picture,
            postedOffers = postedOffers,
            wishlist = wishlist
        )
    }

    constructor(googleInfo: GoogleUserInfo)
            : this(UUID.randomUUID().toString(), Role.USER, emptyList(), emptyList(), googleInfo)
}

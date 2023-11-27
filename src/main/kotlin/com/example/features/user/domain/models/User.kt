package com.example.features.user.domain.models

import com.example.features.authentication.domain.model.UserGoogleInfo
import com.example.features.user.presentation.models.BasicUserDTO
import com.example.features.user.presentation.models.DetailedUserDTO
import com.example.models.Offer
import com.example.utils.Role
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import java.util.UUID

data class User(
    @BsonId
    val id: String = UUID.randomUUID().toString(),
    val role: Role,
    @SerialName("wish_list") val wishlist: List<Offer>,
    @SerialName("posted_offers") val postedOffers: List<Offer>,
    val googleInfo: UserGoogleInfo
){
    fun getUserWithUpdatedInfo(userInfo: UserGoogleInfo): User {
        return this.copy(googleInfo = googleInfo)
    }

    fun toBasicDTO(): BasicUserDTO {
        return BasicUserDTO(
            id = id,
            givenName = googleInfo.givenName,
            picture = googleInfo.picture,
            postedOffers = postedOffers
        )
    }

    fun toDetailedDTO(): DetailedUserDTO {
        return DetailedUserDTO(
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

    constructor(googleInfo: UserGoogleInfo)
            : this(UUID.randomUUID().toString(), Role.USER, emptyList(), emptyList(), googleInfo)
}

package com.example.features.user.domain.models

import com.example.features.authentication.domain.model.UserGoogleInfo
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

    constructor(googleInfo: UserGoogleInfo)
            : this(UUID.randomUUID().toString(), Role.USER, emptyList(), emptyList(), googleInfo)
}

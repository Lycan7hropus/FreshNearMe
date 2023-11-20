package com.example.features.user.domain.models

import com.example.features.user.presentation.models.UserRequest
import com.example.models.Offer
import com.example.utils.Role
import org.bson.codecs.pojo.annotations.BsonId

data class User(
    @BsonId
    val id: String,
    val role: Role,
    val wishlist: List<Offer>,
    val postedOffers: List<Offer>
) {
    constructor(userInfo: UserRequest) : this(
        id = userInfo.id,
        role = Role.USER,
        wishlist = listOf(),
        postedOffers = listOf()
    )

}

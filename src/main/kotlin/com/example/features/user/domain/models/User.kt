package com.example.features.user.domain.models

import com.example.features.user.presentation.models.UserRequest
import com.example.models.Offer

data class User(
    val id: String,
    val wishlist: List<Offer>,
    val postedOffers: List<Offer>
) {
    constructor(userInfo: UserRequest) : this(
        id = userInfo.id,
        wishlist = listOf(),
        postedOffers = listOf()
    )

}

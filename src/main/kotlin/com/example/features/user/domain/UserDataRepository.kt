package com.example.features.user.domain

import com.example.features.authentication.domain.model.UserGoogleInfo
import com.example.features.offer.domain.Offer
import com.example.features.user.domain.models.User

interface UserDataRepository {
    suspend fun getUser(userId: String): User
    suspend fun getUserWishList(userId: String): List<Offer>
    suspend fun updateUserWishlist(userId: String, list: List<Offer>): List<Offer>
    suspend fun getUserOffers(userId: String): List<Offer>
    suspend fun updateUser(user: User): Boolean
    suspend fun updateUserInfo(userInfo: UserGoogleInfo): User

    suspend fun findUserByGoogleId(googleId: String): User
    suspend fun saveUser(user: User): User
    suspend fun updateUserOffers(userId: String, offers: List<Offer>): List<Offer>
}

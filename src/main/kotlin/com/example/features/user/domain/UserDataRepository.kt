package com.example.features.user.domain

import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.models.Offer

interface UserDataRepository {
    suspend fun getUser(userId: String): User
    suspend fun getUserWishList(userId: String): List<Offer>
    suspend fun getUserOffers(userId: String): List<Offer>
    suspend fun updateUser(user: User): Boolean
    suspend fun updateUserInfo(userInfo: UserRequest): Boolean
    suspend fun saveUser(userInfo: UserRequest): User
}

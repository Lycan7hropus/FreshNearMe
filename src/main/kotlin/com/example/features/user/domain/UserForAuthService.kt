package com.example.features.user.domain

import com.example.features.authentication.domain.model.UserGoogleInfo
import com.example.features.user.domain.models.User

//USER ACL
interface UserForAuthService {
    suspend fun getUserById(userId: String): User
    suspend fun updateUser(userGoogleInfo: UserGoogleInfo): User
    suspend fun saveUser(userGoogleInfo: UserGoogleInfo): User
}
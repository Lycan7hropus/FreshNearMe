package com.example.features.user.domain

import com.example.features.authentication.domain.model.GoogleUserInfo
import com.example.features.user.domain.models.User
import com.example.utils.Role

//USER ACL
interface UserService {
    suspend fun getUserById(userId: String): User
    suspend fun updateUser(googleUserInfo: GoogleUserInfo): User
    suspend fun saveUser(googleUserInfo: GoogleUserInfo): User
    suspend fun getRole(userId: String): Role
    suspend fun findUserByGoogleId(googleId: String): User
}
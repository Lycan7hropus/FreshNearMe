package com.example.features.user.data

import com.example.features.authentication.domain.model.UserGoogleInfo
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.UserForAuthService
import com.example.features.user.domain.models.User

class UserForAuthServiceImpl(private val userDataRepository: UserDataRepository) : UserForAuthService {
    override suspend fun getUserById(userId: String): User {
        return userDataRepository.getUser(userId)
    }

    override suspend fun updateUser(userGoogleInfo: UserGoogleInfo): User {
        return userDataRepository.updateUserInfo(userGoogleInfo)
    }

    override suspend fun saveUser(userGoogleInfo: UserGoogleInfo): User {
        val user = User(userGoogleInfo)
        return userDataRepository.saveUser(user)
    }
}
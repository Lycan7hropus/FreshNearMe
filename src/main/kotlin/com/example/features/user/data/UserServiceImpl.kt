package com.example.features.user.data

import com.example.features.authentication.domain.model.GoogleUserInfo
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.UserService
import com.example.features.user.domain.models.User
import com.example.utils.Role

class UserServiceImpl(private val userDataRepository: UserDataRepository) : UserService {
    override suspend fun getUserById(userId: String): User {
        return userDataRepository.getUser(userId)
    }

    override suspend fun updateUser(googleUserInfo: GoogleUserInfo): User {
        return userDataRepository.updateUserInfo(googleUserInfo)
    }

    override suspend fun saveUser(googleUserInfo: GoogleUserInfo): User {
        val user = User(googleUserInfo)
        return userDataRepository.saveUser(user)
    }

    override suspend fun getRole(userId: String): Role {
        return userDataRepository.getRole(userId)
    }

    override suspend fun findUserByGoogleId(googleId: String): User {
        return userDataRepository.findUserByGoogleId(googleId)
    }
}
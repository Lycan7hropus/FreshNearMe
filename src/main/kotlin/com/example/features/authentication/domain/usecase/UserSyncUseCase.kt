package com.example.features.authentication.domain.usecase

import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.user.domain.UserService
import com.example.features.user.domain.models.User


class UserSyncUseCase(
    private val userService: UserService,
    private val googleUserInfoService: GoogleAuthService
) {
    suspend operator fun invoke(userId: String, token: String): User {
        val userGoogleInfo = googleUserInfoService.getUserInfo(token)
        val existingUser: User? = try {
            userService.getUserById(userId)
        } catch (e: Exception) {
            null
        }

        return if (existingUser != null) {
            userService.updateUser(userGoogleInfo)
        } else {
            userService.saveUser(userGoogleInfo)
        }
    }
}

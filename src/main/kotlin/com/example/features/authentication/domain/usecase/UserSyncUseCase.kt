package com.example.features.authentication.domain.usecase

import com.example.features.authentication.domain.GoogleUserInfoService
import com.example.features.user.domain.UserForAuthService
import com.example.features.user.domain.models.User


class UserSyncUseCase(
    private val userForAuthService: UserForAuthService,
    private val googleUserInfoService: GoogleUserInfoService
) {
    suspend fun invoke(userId: String, token: String): User {
        val userGoogleInfo = googleUserInfoService.getUserInfo(token)
        val existingUser: User? = try {
            userForAuthService.getUserById(userId)
        } catch (e: Exception) {
            null
        }

        return if (existingUser != null) {
            userForAuthService.updateUser(userGoogleInfo)
        } else {
            userForAuthService.saveUser(userGoogleInfo)
        }
    }
}

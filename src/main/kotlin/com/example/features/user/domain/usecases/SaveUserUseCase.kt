package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest

class SaveUserUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userInfo: UserRequest): User {
        return userRepository.saveUser(userInfo)
    }
}
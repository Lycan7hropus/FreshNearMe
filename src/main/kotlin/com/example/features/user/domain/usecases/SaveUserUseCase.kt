package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.UserRequest

class SaveUserUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userInfo: UserRequest): Boolean {
        return userRepository.saveUser(userInfo)
    }
}
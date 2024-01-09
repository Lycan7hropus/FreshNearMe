package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User

class SaveUserUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(user: User): User {
        return userRepository.saveUser(user)
    }
}
package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User

class GetUserUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(id: String): User {
        return userRepository.getUser(id)
    }
}
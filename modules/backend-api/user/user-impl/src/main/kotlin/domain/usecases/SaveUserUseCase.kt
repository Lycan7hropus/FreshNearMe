package com.example.features.user.domain.usecases

import domain.UserDataRepository
import domain.models.User

internal class SaveUserUseCase(private val userRepository: domain.UserDataRepository) {
    suspend fun invoke(user: User): User {
        return userRepository.saveUser(user)
    }
}
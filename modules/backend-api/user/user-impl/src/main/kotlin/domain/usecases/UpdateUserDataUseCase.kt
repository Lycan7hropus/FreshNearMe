package com.example.features.user.domain.usecases

import domain.UserDataRepository
import domain.models.User

internal class UpdateUserDataUseCase(private val userRepository: domain.UserDataRepository) {
    suspend fun invoke(updatedUser: User): Boolean {
        return userRepository.updateUser(updatedUser)
    }
}
package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User

class UpdateUserDataUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(updatedUser: User): Boolean {
        return userRepository.updateUser(updatedUser)
    }
}
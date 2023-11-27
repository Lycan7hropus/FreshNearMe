package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.BasicUserDTO
import com.example.features.user.presentation.models.DetailedUserDTO

class GetUserUseCase(private val userRepository: UserDataRepository) {
    suspend fun getBasicInfo(id: String): BasicUserDTO {
        return userRepository.getUser(id).toBasicDTO()
    }
    suspend fun getDetailedInfo(id: String): DetailedUserDTO {
        return userRepository.getUser(id).toDetailedDTO()
    }
}


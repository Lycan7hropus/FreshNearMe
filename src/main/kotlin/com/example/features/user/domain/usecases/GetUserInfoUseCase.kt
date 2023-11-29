package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.BasicUserDto
import com.example.features.user.presentation.models.DetailedUserDto

class GetUserInfoUseCase(private val userRepository: UserDataRepository) {
    suspend fun getBasicInfo(id: String): BasicUserDto {
        return userRepository.getUser(id).toBasicDTO()
    }

    suspend fun getDetailedInfo(id: String): DetailedUserDto {
        return userRepository.getUser(id).toDetailedDTO()
    }
}


package com.example.features.auth.domain

import com.example.features.auth.presentation.models.AuthEventDto
import com.example.features.user.presentation.models.BasicUserDto

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {

    override fun handleUserRegistrationEvent(authEventDto: AuthEventDto):BasicUserDto {
        val user = authEventDto.toUser()

        return authRepository.saveUser(user).toBasicDTO()
    }

    override fun handleUserLoginEvent(authEventDto: AuthEventDto):BasicUserDto {
        val user = authEventDto.toUser()

        return authRepository.updateUser(user).toBasicDTO()
    }
}
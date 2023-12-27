package com.example.features.auth.domain

import com.example.features.auth.presentation.models.AuthEventDto
import com.example.features.user.presentation.models.BasicUserDto

interface AuthService {

    fun handleUserRegistrationEvent(authEventDto: AuthEventDto): BasicUserDto

    fun handleUserLoginEvent(authEventDto: AuthEventDto): BasicUserDto

}
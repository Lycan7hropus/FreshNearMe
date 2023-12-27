package com.example.features.auth.presentation


import com.example.features.auth.domain.AuthService
import com.example.features.auth.presentation.models.AuthEventDto
import com.example.utils.extensionFunctions.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Route.authRoutes(authService: AuthService) {
    post<AuthEventDto>("/register") { authEventDto ->
        val offersResponse = authService.handleUserRegistrationEvent(authEventDto)
        call.respondSuccess(data = offersResponse)
    }


    post<AuthEventDto>("/login") { authEventDto ->
        val offersResponse = authService.handleUserLoginEvent(authEventDto)
        call.respondSuccess(data = offersResponse)
    }

}
package com.example.features.auth.presentation

import com.example.features.auth.presentation.models.AuthEventDto

import io.ktor.server.routing.*


fun Route.authRoutes() {
    post<AuthEventDto>("/register") { authEventDto ->


    }


    post<AuthEventDto>("/login") { authEventDto ->


    }

}
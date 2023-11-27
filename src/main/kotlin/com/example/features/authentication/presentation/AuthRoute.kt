package com.example.features.authentication.presentation


import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.utils.getBearerToken
import com.example.utils.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import javax.naming.AuthenticationException

fun Route.authRoutes(userSyncUseCase: UserSyncUseCase) {

    authenticate("auth-bearer") {
        post("/auth/sync") {
            val userId =
                call.principal<UserIdPrincipal>()?.name ?: throw AuthenticationException("Authentication went wrong")
            val token = call.getBearerToken()
            val result = userSyncUseCase.invoke(userId, token)
            call.respondSuccess(result)
        }

    }
}


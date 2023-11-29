package com.example.features.authentication.presentation


import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.utils.extensionFunctions.getBearerToken
import com.example.utils.extensionFunctions.getUserId
import com.example.utils.extensionFunctions.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.authRoutes(userSyncUseCase: UserSyncUseCase) {
    route("/auth"){
        authenticate("auth-bearer") {
            post("/sync") {
                val userId = call.getUserId()
                val token = call.getBearerToken()
                val result = userSyncUseCase(userId, token)
                call.respondSuccess(result)
            }

        }
    }
}


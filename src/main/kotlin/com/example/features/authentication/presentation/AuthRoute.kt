package com.example.features.authentication.presentation


import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.utils.extensionFunctions.getBearerToken
import com.example.utils.extensionFunctions.getUserId
import com.example.utils.extensionFunctions.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

fun Route.authRoutes(userSyncUseCase: UserSyncUseCase = getKoin().get()) {
    route("/auth"){
        authenticate("auth-bearer") {
            post("/sync") {
                val userId = call.getUserId()
                val token = call.getBearerToken()
                val result = userSyncUseCase(userId, token)
                call.respondSuccess(result)
            }
        }

        post("/login") {
            val userId = call.getUserId()
            val token = call.getBearerToken()
            val result = userSyncUseCase(userId, token)
            call.respondSuccess(result)
        }

        post("/register") {
            val userId = call.getUserId()
            val token = call.getBearerToken()
            val result = userSyncUseCase(userId, token)
            call.respondSuccess(result)
        }
    }
}


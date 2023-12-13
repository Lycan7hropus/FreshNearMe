package com.example.plugins

import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.user.domain.UserService
import com.example.models.UserPrincipal
import com.example.utils.Role
import io.ktor.server.application.*
import io.ktor.server.auth.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.getKoin
import org.koin.ktor.ext.inject

fun Application.configureSecurity(googleAuthenticationService: GoogleAuthService = getKoin().get(), userService: UserService = getKoin().get()) {
    install(Authentication) {
        bearer("auth-bearer") {
            realm = "Access to the '/' path"
            authenticate { tokenCredential ->
                val googleId = googleAuthenticationService.verifyGoogleToken(tokenCredential.token)
                val userId = userService.findUserByGoogleId(googleId).id
                val userRole = userService.getRole(userId)
                UserPrincipal(userId, userRole)
            }
        }
    }
}



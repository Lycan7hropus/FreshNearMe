package com.example.plugins

import com.auth0.jwk.JwkProviderBuilder
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.concurrent.TimeUnit

fun validateCreds(credential: JWTCredential, permission: String? = null): JWTPrincipal? {
    val containsAudience = credential.payload.audience.contains(System.getenv("AUDIENCE"))
    val containsScope = permission.isNullOrBlank() ||
            credential.payload.claims["permissions"]?.asArray(String::class.java)?.contains(permission) == true

    if (containsAudience && containsScope) {
        return JWTPrincipal(credential.payload)
    }

    return null
}
fun Application.configureAuth0() {

    val jwkProvider = JwkProviderBuilder(System.getenv("ISSUER"))
        .cached(10, 24, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt("auth0") {
            verifier(jwkProvider, System.getenv("ISSUER"))
            validate { credential -> validateCreds(credential) }
        }
        jwt("auth0-admin") {
            verifier(jwkProvider, System.getenv("ISSUER"))
            validate { credential -> validateCreds(credential, "read:admin-messages") }
        }
    }
}

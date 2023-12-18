package com.example.utils.extensionFunctions

import com.example.models.JwtUserPrincipal
import io.ktor.server.auth.jwt.*
import java.util.*

fun JWTCredential.toJwtUserPrincipal(): JwtUserPrincipal? {
    return if (isValidToken()) {
        payload.toJwtUserPrincipal()
    } else null
}

fun JWTCredential.isValidToken() = expiresAt?.after(Date()) == true

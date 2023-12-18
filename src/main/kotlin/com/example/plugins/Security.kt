package com.example.plugins

import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.interfaces.Payload
import com.example.models.JwtUserPrincipal
import com.example.utils.Role
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.net.URL
import java.util.*
import javax.naming.AuthenticationException

fun Application.configureSecurity() {
    val myRealm = "fresh-near-me"
    val issuer = "http://localhost:18080/realms/$myRealm"
    val client = "fresh-near-me-client"
    val audience = "account" // Adjusted to match the token's audience
    val jwkProvider = "http://localhost:18080/realms/$myRealm/protocol/openid-connect/certs"

    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                UrlJwkProvider(URL(jwkProvider)),
                issuer
            ) {
                withAudience(audience)
                withClaim("azp", client)
            }
            challenge { _, _ ->
                throw AuthenticationException("Token is not valid or has expired")
            }
            validate { credential -> credential.toJwtUserPrincipal() }
        }
    }
}


private fun JWTCredential.toJwtUserPrincipal(): JwtUserPrincipal? {
    return if (isValidToken()) {
        payload.toJwtUserPrincipal()
    } else null
}

private fun JWTCredential.isValidToken() = expiresAt?.after(Date()) == true

private fun Payload.toJwtUserPrincipal() = JwtUserPrincipal(
    exp = expiresAt?.time,
    roles = getRoles(),
    email = getClaim("email").asString(),
    familyName = getClaim("family_name").asString(),
    givenName = getClaim("given_name").asString(),
    preferredUsername = getClaim("preferred_username").asString(),
    name = getClaim("name").asString(),
    emailVerified = getClaim("email_verified").asBoolean(),
    sid = getClaim("sid").asString()
)

private fun Payload.getRoles(): List<Role> {
    val rolesList = getClaim("realm_access").asMap()["roles"] as? List<String> ?: return emptyList()
    return rolesList.mapNotNull { roleName ->
        Role.fromRoleStr(roleName)
    }
}
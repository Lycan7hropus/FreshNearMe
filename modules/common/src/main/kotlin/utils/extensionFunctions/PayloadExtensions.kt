package com.example.utils.extensionFunctions

import com.auth0.jwt.interfaces.Payload
import com.example.utils.models.JwtUserPrincipal
import com.example.utils.Role

fun Payload.toJwtUserPrincipal() = JwtUserPrincipal(
    exp = expiresAt?.time,
    roles = getRoles(),
    email = getClaim("email").asString(),
    familyName = getClaim("family_name").asString(),
    givenName = getClaim("given_name").asString(),
    preferredUsername = getClaim("preferred_username").asString(),
    name = getClaim("name").asString(),
    emailVerified = getClaim("email_verified").asBoolean(),
    sid = getClaim("sid").asString(),
    sub = getClaim("sub").asString(),
)

fun Payload.getRoles(): List<Role> {
    val rolesList = getClaim("realm_access").asMap()["roles"] as? List<String> ?: return emptyList()
    return rolesList.mapNotNull { roleName ->
        com.example.utils.Role.fromRoleStr(roleName)
    }
}
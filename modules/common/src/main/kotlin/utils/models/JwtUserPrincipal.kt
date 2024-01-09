package com.example.utils.models

import com.example.utils.Role
import io.ktor.server.auth.*

data class JwtUserPrincipal(
    val exp: Long?,
    val roles: List<Role>, // Z realm_access
    val email: String?,
    val familyName: String?, // family_name
    val givenName: String?, // given_name
    val preferredUsername: String?, // preferred_username
    val name: String?,
    val emailVerified: Boolean?, // email_verified
    val sid: String?,
    val sub: String?
) : Principal
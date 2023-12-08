package com.example.models

import com.example.utils.Role
import io.ktor.server.auth.*

public data class UserPrincipal(val userId: String, val role: Role) : Principal
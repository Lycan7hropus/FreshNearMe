package com.example.models

import com.example.utils.Role
import io.ktor.server.auth.*

data class UserPrincipal(val userId: String, val role: Role) : Principal
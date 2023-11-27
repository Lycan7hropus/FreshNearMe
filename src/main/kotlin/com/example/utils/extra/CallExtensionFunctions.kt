package com.example.utils.extra

import io.ktor.http.*
import io.ktor.server.application.*
import javax.naming.AuthenticationException

fun ApplicationCall.getBearerToken(): String{
    return request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ") ?: throw AuthenticationException("Invalid auth header")
}
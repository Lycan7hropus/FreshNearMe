package com.example.utils.extra

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import javax.naming.AuthenticationException

fun ApplicationCall.getBearerToken(): String{
    return request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ") ?: throw AuthenticationException("Invalid auth header")
}
fun ApplicationCall.getUserId(): String{
    return this.principal<UserIdPrincipal>()?.name ?: throw AuthenticationException("Authentication went wrong")
}
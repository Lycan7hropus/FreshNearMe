package com.example.utils.extensionFunctions

import com.example.models.UserPrincipal
import com.example.utils.ApiResponse
import com.example.utils.Role
import com.example.utils.handleException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import javax.naming.AuthenticationException

fun ApplicationCall.getBearerToken(): String {
    return request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ")
        ?: throw AuthenticationException("Invalid auth header")
}

fun ApplicationCall.getUserId(): String {
    return this.principal<UserPrincipal>()?.userId ?: throw AuthenticationException("Authentication went wrong")
}

fun ApplicationCall.getUserRole(): Role {
    return this.principal<UserPrincipal>()?.role ?: throw AuthenticationException("Authentication went wrong")
}

suspend fun <T> ApplicationCall.respondSuccess(data: T, code: HttpStatusCode = HttpStatusCode.OK) {
    val response = ApiResponse.Success(data)
    this.respond(code, response)
}

suspend fun ApplicationCall.respondError(exception: Exception, code: HttpStatusCode? = null) {
    val (status, apiError) = handleException(exception)
    val response = ApiResponse.Error(apiError)
    this.respond(code ?: status, response)
}

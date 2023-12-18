package com.example.utils.extensionFunctions

import com.example.models.JwtUserPrincipal
import com.example.utils.ApiResponse
import com.example.utils.Role
import com.example.utils.handleException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import javax.naming.AuthenticationException


fun ApplicationCall.getUserId(): String {
    return this.principal<JwtUserPrincipal>()?.sid ?: throw AuthenticationException("Authentication went wrong")
}

fun ApplicationCall.getUserRole(): Role {
    return this.principal<JwtUserPrincipal>()?.roles?.first() ?: throw AuthenticationException("Authentication went wrong")
}
fun ApplicationCall.getUserRoles(): List<Role> {
    return this.principal<JwtUserPrincipal>()?.roles ?: throw AuthenticationException("Authentication went wrong")
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

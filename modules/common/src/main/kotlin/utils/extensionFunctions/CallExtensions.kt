package utils.extensionFunctions

import utils.ApiResponse
import utils.InvalidPrincipalException
import utils.handleException
import utils.models.JwtUserPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import utils.Role


fun ApplicationCall.getUserId(): String {
    return this.principal<JwtUserPrincipal>()?.sub
        ?: throw InvalidPrincipalException("User ID extraction failed: JWT token may be invalid or missing.")
}

fun ApplicationCall.getUserRoles(): List<Role> {
    return this.principal<JwtUserPrincipal>()?.roles
        ?: throw InvalidPrincipalException("User roles extraction failed: JWT token may be invalid or missing.")
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

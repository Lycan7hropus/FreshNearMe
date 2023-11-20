package com.example.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import java.util.concurrent.TimeoutException
import javax.naming.AuthenticationException

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
}

data class ApiError(
    val type: String,
    val message: String,
    val details: String? = null
)

data class ExceptionResponse(
    val status: HttpStatusCode,
    val error: ApiError
)

suspend fun <T> ApplicationCall.respondSuccess( data: T, code: HttpStatusCode = HttpStatusCode.OK,) {
    val response = ApiResponse.Success(data)
    this.respond(code, response)
}

suspend fun ApplicationCall.respondError(exception: Exception, code: HttpStatusCode? = null) {
    val (status, apiError) = handleException(exception)
    val response = ApiResponse.Error(apiError)
    this.respond(code ?: status, response)
}

fun handleException(e: Exception): ExceptionResponse {
    return when (e) {
        is IllegalArgumentException -> ExceptionResponse(
            status = HttpStatusCode.BadRequest,
            error = ApiError(
                type = "validation-error",
                message = "Invalid arguments",
                details = e.message
            )
        )
        is AuthenticationException -> ExceptionResponse(
            status = HttpStatusCode.Unauthorized,
            error = ApiError(
                type = "authentication-error",
                message = "Authentication failed",
                details = e.message
            )
        )
        is AccessDeniedException -> ExceptionResponse(
            status = HttpStatusCode.Forbidden,
            error = ApiError(
                type = "access-denied",
                message = "Access denied",
                details = e.message
            )
        )
        is NotFoundException -> ExceptionResponse(
            status = HttpStatusCode.NotFound,
            error = ApiError(
                type = "not-found",
                message = "Resource not found",
                details = e.message
            )
        )
        is TimeoutException -> ExceptionResponse(
            status = HttpStatusCode.RequestTimeout,
            error = ApiError(
                type = "timeout",
                message = "Request timed out",
                details = e.message
            )
        )
        // Tutaj możesz dodać więcej niestandardowych wyjątków lub obsłużyć inne standardowe wyjątki
        else -> ExceptionResponse(
            status = HttpStatusCode.InternalServerError,
            error = ApiError(
                type = "internal-error",
                message = "Internal server error",
                details = e.message ?: "Unknown error"
            )
        )
    }
}

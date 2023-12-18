package com.example.utils

import io.ktor.http.*
import io.ktor.server.plugins.*
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeoutException
import javax.naming.AuthenticationException

@Serializable
sealed class ApiResponse<out T> {
    @Serializable
    data class Success<T>(val data: T) : ApiResponse<T>()

    @Serializable
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
}

@Serializable
data class ApiError(
    val type: String,
    val message: String?
)

data class ExceptionResponse(
    val status: HttpStatusCode,
    val error: ApiError
)

fun handleException(e: Exception): ExceptionResponse {
    return when (e) {
        is IllegalArgumentException -> ExceptionResponse(
            status = HttpStatusCode.BadRequest,
            error = ApiError(
                type = "validation-error",
                message = e.message
            )
        )

        is AuthenticationException -> ExceptionResponse(
            status = HttpStatusCode.Unauthorized,
            error = ApiError(
                type = "authentication-error",
                message = e.message
            )
        )

        is ResourceAccessDenied -> ExceptionResponse(
            status = HttpStatusCode.Forbidden,
            error = ApiError(
                type = "access-denied",
                message = e.message
            )
        )

        is NotFoundException -> ExceptionResponse(
            status = HttpStatusCode.NotFound,
            error = ApiError(
                type = "not-found",
                message = e.message
            )
        )

        is TimeoutException -> ExceptionResponse(
            status = HttpStatusCode.RequestTimeout,
            error = ApiError(
                type = "timeout",
                message = e.message
            )
        )

        is OfferCreationException -> ExceptionResponse(
            status = HttpStatusCode.BadRequest,
            error = ApiError(
                type = "offer-creation-error",
                message = e.message
            )
        )

        is ValidationException -> ExceptionResponse(
            status = HttpStatusCode.UnprocessableEntity,
            error = ApiError(
                type = "validation-error",
                message = e.message
            )
        )

        is UserSavingException -> ExceptionResponse(
            status = HttpStatusCode.InternalServerError,
            error = ApiError(
                type = "user-saving-error",
                message = e.message
            )
        )

        is UserAlreadyExistsException -> ExceptionResponse(
            status = HttpStatusCode.Conflict,
            error = ApiError(
                type = "user-already-exists",
                message = e.message
            )
        )

        is InternalErrorException -> ExceptionResponse(
            status = HttpStatusCode.InternalServerError,
            error = ApiError(
                type = "internal-error",
                message = e.message
            )
        )

        is UnauthorizedAccessException -> ExceptionResponse(
            status = HttpStatusCode.Forbidden,
            error = ApiError(
                type = "authorization-error",
                message = e.message
            )
        )

        else -> ExceptionResponse(
            status = HttpStatusCode.InternalServerError,
            error = ApiError(
                type = "undefined-exception",
                message = e.message ?: "Unknown error"
            )
        )
    }
}

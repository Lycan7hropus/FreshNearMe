package com.example.plugins

import utils.InternalErrorException
import utils.extensionFunctions.respondError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when (throwable) {
                is Error -> {
                    call.respondError(InternalErrorException("A critical error occurred"))
                }

                is Exception -> {
                    call.respondError(throwable)
                }

                else -> {
                    call.respondError(Exception("Undefined exception"))
                }
            }
        }
        status(HttpStatusCode.NotFound) { call, _ ->
            val path = call.request.path()
            val method = call.request.httpMethod.value
            call.respondError(NotFoundException("Resource not found at path: $method $path"), HttpStatusCode.NotFound)
        }
        status(HttpStatusCode.Unauthorized) { call, _ ->
            val path = call.request.path()
            val method = call.request.httpMethod.value
            call.respondError(AuthenticationException("To access the resource request needs to be authenticated"))
        }

    }
}
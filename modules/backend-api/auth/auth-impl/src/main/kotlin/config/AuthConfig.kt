package config


import io.ktor.server.application.*
import io.ktor.server.routing.*
import presentation.authRoutes

fun Application.authConfig(){
    routing {
        authRoutes()
    }
}
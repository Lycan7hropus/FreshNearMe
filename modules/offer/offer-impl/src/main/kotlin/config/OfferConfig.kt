package config

import di.offerModule
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import presentation.offerRoutes

fun Application.offerConfig(){
    install(Koin) {
        slf4jLogger()
        modules(listOf(offerModule))
    }
    routing {
        offerRoutes()
    }
}
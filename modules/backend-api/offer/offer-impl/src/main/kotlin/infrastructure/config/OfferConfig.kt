package infrastructure.config

import infrastructure.di.offerModule
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import presentation.offerRoutes

fun Application.offerConfig(){
    getKoin().loadModules(listOf(offerModule))
    routing {
        offerRoutes()
    }
}
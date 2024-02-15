package config


import presentation.userRoutes
import di.userModule
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

fun Application.userConfig(){
    getKoin().loadModules(listOf(userModule))
    routing {
        userRoutes()
    }
}
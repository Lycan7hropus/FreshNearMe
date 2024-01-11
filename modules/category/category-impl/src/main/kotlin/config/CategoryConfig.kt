package config

import di.categoryModule
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import presentation.categoryRoutes

fun Application.categoryConfig(){
    getKoin().loadModules(listOf(categoryModule))
    routing {
        categoryRoutes()
    }
}
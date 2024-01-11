package config


import com.example.features.user.presentation.userRoutes
import di.userModule
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

fun Application.categoryConfig(){
    getKoin().loadModules(listOf(userModule))
    routing {
        userRoutes()
    }
}
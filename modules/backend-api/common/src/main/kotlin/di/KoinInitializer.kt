package di


import di.appModule
import di.databaseModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.installKoin() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(appModule, databaseModule))
    }
}
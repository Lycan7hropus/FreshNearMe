package com.example


import com.example.plugins.*
import config.authConfig
import config.userConfig
import infra.config.categoryConfig
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    initKoin()
    configureSecurity()
    configureSerialization()
    configureStatusPages()
    configureCORS()

    userConfig()
    categoryConfig()
    authConfig()

}

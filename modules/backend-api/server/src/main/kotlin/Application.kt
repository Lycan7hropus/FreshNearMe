package com.example

import Infrastructure.config.offerConfig
import com.example.plugins.*
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


    offerConfig()
    userConfig()
    categoryConfig()

}

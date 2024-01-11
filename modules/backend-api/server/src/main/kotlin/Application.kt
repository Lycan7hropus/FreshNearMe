package com.example

import di.installKoin
import com.example.plugins.*
import config.categoryConfig
import config.offerConfig
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    installKoin()
    configureSecurity()
    configureSerialization()
    configureStatusPages()
    configureCORS()


    offerConfig()
    categoryConfig()
}

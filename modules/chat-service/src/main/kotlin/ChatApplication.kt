package com.example


import com.example.chat.config.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    initKoin()
    configureSecurity()
    configureStatusPages()
    configureSerialization()
    configureCORS()
    configureSockets()
    configureRouting()
}

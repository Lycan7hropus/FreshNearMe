package com.example

import com.example.di.initKoin
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    initKoin()
    configureSerialization()
    configureOAuth()
    configureRouting()
}

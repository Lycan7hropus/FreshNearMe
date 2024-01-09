package com.example

import com.example.di.initKoin
import com.example.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    initKoin()
    configureSecurity()
    configureSerialization()
    configureStatusPages()
    configureRouting()
    configureCORS()

}

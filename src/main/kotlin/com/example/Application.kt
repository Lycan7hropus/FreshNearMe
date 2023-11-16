package com.example

import com.example.di.initKoin
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)

}


// Your Application.module where you configure your Ktor application
fun Application.module() {
    initKoin()
    configureSerialization()
    //configureOAuth()
    configureAuth0()
    configureRouting()

}

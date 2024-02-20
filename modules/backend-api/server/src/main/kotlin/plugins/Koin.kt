package com.example.plugins



import di.commonModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.initKoin() {
    install(Koin) {
        modules(commonModule)
    }
}
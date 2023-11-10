package com.example.di

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.initKoin() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(appModule, databaseModule, offerModule))
    }
}
package com.example.di

import com.example.di.features.categoryModule
import com.example.di.features.offerModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.initKoin() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(databaseModule, offerModule, categoryModule))
    }
}
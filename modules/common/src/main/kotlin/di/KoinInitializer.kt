package com.example.di

import com.example.di.features.categoryModule
import com.example.di.features.offerModule
import com.example.di.features.userModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.initKoin() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(appModule, databaseModule, categoryModule, offerModule, userModule, ))
    }
}
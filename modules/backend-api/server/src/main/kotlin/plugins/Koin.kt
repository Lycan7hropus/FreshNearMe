package com.example.plugins


import infra.di.categoryModule
import di.commonModule
import infrastructure.di.offerModule
import di.userModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.initKoin() {
    install(Koin) {
        modules(commonModule, offerModule, categoryModule, userModule)
    }
}
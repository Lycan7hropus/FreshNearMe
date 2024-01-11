package com.example.chat.config


import com.example.chat.di.chatModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.initKoin() {
    install(Koin) {
        modules(listOf(chatModule))
    }
}
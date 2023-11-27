package com.example.di

import io.ktor.client.*
import org.koin.dsl.module
import org.slf4j.LoggerFactory

val appModule = module {
    single { LoggerFactory.getLogger("logger") }
    single { HttpClient() }
}

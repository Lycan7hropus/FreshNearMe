package com.example.di.features

import com.example.features.authentication.data.GoogleAuthServiceImpl
import com.example.features.authentication.domain.usecase.UserSyncUseCase
import org.koin.dsl.module

val testAuthModule = module {
    single { GoogleAuthServiceImpl(get()) }
    single { UserSyncUseCase(get(), get()) }
}
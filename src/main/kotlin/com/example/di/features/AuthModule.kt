package com.example.di.features

import com.example.features.authentication.data.GoogleUserInfoServiceImpl
import com.example.features.authentication.domain.usecase.UserSyncUseCase
import org.koin.dsl.module

val authModule = module {
    single { GoogleUserInfoServiceImpl(get()) }
    single { UserSyncUseCase(get(), get()) }
}
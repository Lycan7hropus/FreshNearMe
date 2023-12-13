package com.example.di.features

import com.example.features.authentication.data.GoogleAuthServiceImpl
import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.features.user.data.UserServiceImpl
import org.koin.dsl.module

val authModule = module {
    single { GoogleAuthServiceImpl(get()) }
    single { UserSyncUseCase(get(), get()) }
    ///single { UserServiceImpl(get()) }???????????? what about this???
}
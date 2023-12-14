package com.example.di.features

import com.example.features.authentication.data.GoogleAuthServiceImpl
import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.features.user.data.UserServiceImpl
import com.example.features.user.domain.UserService
import org.koin.dsl.module

val authModule = module {
    single<GoogleAuthService> { GoogleAuthServiceImpl(get()) }
    single { UserSyncUseCase(get(), get()) }
    single <UserService>{ UserServiceImpl(get()) }
}
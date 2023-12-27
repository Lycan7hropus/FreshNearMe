package com.example.di.features

import com.example.features.auth.data.AuthRepositoryImpl
import com.example.features.auth.domain.AuthRepository
import com.example.features.auth.domain.AuthService
import com.example.features.auth.domain.AuthServiceImpl
import com.example.features.user.data.UserDataRepositoryImpl
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(named("UserCollection"))) }

    single { AuthServiceImpl(get()) }
}
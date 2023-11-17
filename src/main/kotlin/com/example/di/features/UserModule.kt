package com.example.di.features


import com.example.features.user.data.UserDataRepositoryImpl
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.usecases.GetUserUseCase
import com.example.features.user.domain.usecases.UpdateUserUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userModule = module {
    single<UserDataRepository> { UserDataRepositoryImpl(get(named("UserCollection"))) }

    single { GetUserUseCase(get()) }
    single { UpdateUserUseCase(get()) }
}
package com.example.di.features


import com.example.features.user.data.UserDataRepositoryImpl
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userModule = module {
    single<UserDataRepository> { UserDataRepositoryImpl(get(named("UserCollection"))) }

    single { GetUserOffersUseCase(get()) }
    single { GetUserUseCase(get()) }
    single { GetUserWishListUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { UpdateUserUseCase(get()) }
}
package com.example.di.features


import com.example.features.user.data.UserDataRepositoryImpl
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.usecases.*
import com.example.services.FakeUserDtoService
import io.mockk.mockk
import org.koin.core.qualifier.named
import org.koin.dsl.module

val testUserModule = module {
    single<UserDataRepository> { mockk(relaxed = true) }

    // Mocking each use case with MockK
    single { mockk<GetUserOffersUseCase>(relaxed = true) }
    single { mockk<GetUserInfoUseCase>(relaxed = true) }
    single { mockk<UserWishlistUseCase>(relaxed = true) }
    single { mockk<SaveUserUseCase>(relaxed = true) }
    single { mockk<UpdateUserDataUseCase>(relaxed = true) }
    single { mockk<FakeUserDtoService>(relaxed = true) }
}


package com.example.di.features

import com.example.features.authentication.data.GoogleAuthServiceImpl
import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.authentication.domain.usecase.UserSyncUseCase
import com.example.features.user.domain.UserService
import io.mockk.mockk
import org.koin.dsl.module

val testAuthModule = module {
    single<GoogleAuthService> { mockk(relaxed = true)  }
    single<UserService> { mockk(relaxed = true)  }
}
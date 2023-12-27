package com.example.di.features

import com.example.features.rating.data.RatingRepositoryImpl
import com.example.features.rating.domain.RatingRepository
import com.example.features.rating.domain.RatingService
import com.example.features.rating.domain.RatingServiceImpl
import com.example.features.user.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ratingModule = module {
    single<RatingRepository> { RatingRepositoryImpl(get(named("RatingCollection"))) }

    single { RatingServiceImpl(get()) }
}
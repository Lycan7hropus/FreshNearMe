package com.example.di.features

import com.example.features.category.domain.CategoryService
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.features.offer.domain.CategoryProvider
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val offerModule = module {
    single<OfferRepository> { OfferRepositoryImpl(get(named("OfferCollection"))) }

    single { CreateOfferUseCase(get(), get()) }
    single { GetOffersUseCase(get(), get()) }
    single { GetOfferByIdUseCase(get()) }
    single { UpdateOfferUseCase(get(), get()) }

    single<CategoryProvider> { CategoryService(get()) }
}
package com.example.di

import com.example.features.offer.data.OfferRepository
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.features.offer.domain.CreateOfferUseCase
import com.example.features.offer.domain.GetOffersUseCase
import com.example.features.offer.domain.GetOfferByIdUseCase
import com.example.features.offer.domain.UpdateOfferUseCase
import org.koin.dsl.module

val offerModule = module {
    single<OfferRepository> { OfferRepositoryImpl(get()) }
    single { CreateOfferUseCase(get()) }
    single { GetOffersUseCase(get()) }
    single { GetOfferByIdUseCase(get()) }
    single { UpdateOfferUseCase(get()) }
}
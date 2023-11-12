package com.example.di.features

import com.example.features.category.data.CategoryRepositoryImpl
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.*
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import org.koin.dsl.module

val categoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single { CreateCategoryUseCase()}
    single { DeleteCategoryUseCase()}
    single { GetAllCategoriesUseCase()}
    single { GetCategoryByIdUseCase()}
    single { UpdateCategoryUseCase()}

}
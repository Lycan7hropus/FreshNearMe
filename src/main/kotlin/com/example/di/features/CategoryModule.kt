package com.example.di.features

import com.example.database.DatabaseProvider
import com.example.features.category.data.CategoryRepositoryImpl
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.*
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.models.Category
import com.example.models.Offer
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val categoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get(named("CategoryCollection"))) }

    single { CreateCategoryUseCase(get()) }
    single { DeleteCategoryUseCase(get()) }
    single { GetAllCategoriesUseCase(get()) }
    single { GetCategoryByIdUseCase() }
    single { UpdateCategoryUseCase() }
}
package com.example.di.features

import com.example.features.category.data.CategoryRepositoryImpl
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val categoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get(named("CategoryCollection"))) }

    single { CreateCategoryUseCase(get()) }
    single { DeleteCategoryUseCase(get()) }
    single { GetAllCategoriesUseCase(get()) }
    single { GetCategoryByIdUseCase() }
    single { UpdateCategoryUseCase() }
}
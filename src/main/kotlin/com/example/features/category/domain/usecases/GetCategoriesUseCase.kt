package com.example.features.category.domain.usecases

import com.example.features.category.domain.CategoryRepository
import com.example.features.category.presentation.dto.CategoriesDto

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): CategoriesDto {
        val categories = categoryRepository.getAllCategories()
        return CategoriesDto(categories)
    }
}
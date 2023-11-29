package com.example.features.category.domain.usecases

import com.example.features.category.domain.Category
import com.example.features.category.domain.CategoryFactory
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.presentation.dto.CategoryDto

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryDto: CategoryDto): Category {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category)
    }
}
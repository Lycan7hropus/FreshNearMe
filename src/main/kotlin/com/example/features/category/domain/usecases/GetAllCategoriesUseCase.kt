package com.example.features.category.domain.usecases

import com.example.features.category.domain.Category
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.presentation.dto.CategoryDTO

class GetAllCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryDto: CategoryDTO): Result<List<Category>> {
        return try {
            val categories = categoryRepository.getAllCategories()
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
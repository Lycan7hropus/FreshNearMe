package com.example.features.category.domain.usecases

import com.example.features.category.domain.CategoryFactory
import com.example.features.category.domain.CategoryRepository
import com.example.models.Category

class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String): Result<Boolean> {
        return try {
            val deletedCategory = categoryRepository.deleteCategory(categoryId)
            Result.success(deletedCategory)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
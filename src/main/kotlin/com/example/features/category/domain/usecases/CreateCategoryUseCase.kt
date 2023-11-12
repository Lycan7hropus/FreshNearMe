package com.example.features.category.domain.usecases

import com.example.features.category.domain.CategoryFactory
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.presentation.dto.CategoryDTO
import com.example.models.Category
import com.example.utils.exceptions.OfferCreationException

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryDto: CategoryDTO): Result<Category> {
        return try {
            val categoryFactory = CategoryFactory(categoryRepository)
            val category = categoryFactory.createCategoryFromDTO(categoryDto)
                ?: throw IllegalArgumentException("Nie udało się utworzyć kategorii z DTO")

            val createdCategory = categoryRepository.saveCategory(category)
                ?: throw Exception("Category could not be created")

            Result.success(createdCategory)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
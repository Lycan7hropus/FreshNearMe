package com.example.utils

import com.example.features.category.domain.CategoryRepository
import com.example.features.offer.domain.CategoryProvider
import com.example.models.Category

class CategoryACL(private val categoryRepository: CategoryRepository) : CategoryProvider {
    override suspend fun getCategoryById(id: String): Category? {
        // Logika tłumacząca lub dostosowująca dane z repozytorium kategorii
        return categoryRepository.getCategoryById(id)
    }
}
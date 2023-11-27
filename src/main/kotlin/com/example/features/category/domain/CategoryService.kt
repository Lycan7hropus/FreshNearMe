package com.example.features.category.domain

import com.example.features.offer.domain.CategoryProvider

class CategoryService(private val categoryRepository: CategoryRepository) : CategoryProvider {
    override suspend fun getCategoryById(id: String): Category? {
        // Logika tłumacząca lub dostosowująca dane z repozytorium kategorii
        return categoryRepository.getCategoryById(id)
    }
}
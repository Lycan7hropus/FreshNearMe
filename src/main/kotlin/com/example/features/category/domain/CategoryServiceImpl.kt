package com.example.features.category.domain

import com.example.features.offer.domain.CategoryService

class CategoryServiceImpl(private val categoryRepository: CategoryRepository) :
    CategoryService {
    override suspend fun getCategoryById(id: String): Category {
        return categoryRepository.getCategoryById(id)
    }
}
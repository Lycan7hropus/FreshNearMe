package com.example.features.offer.domain

import com.example.features.category.domain.Category

interface CategoryService {
    suspend fun getCategoryById(id: String): Category
}
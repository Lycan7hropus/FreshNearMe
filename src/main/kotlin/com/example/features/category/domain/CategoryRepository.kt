package com.example.features.category.domain

import com.example.models.Category
import org.litote.kmongo.coroutine.CoroutineCollection

interface CategoryRepository{
    suspend fun createCategory(category: Category): Category?
    suspend fun getCategoryById(id: String): Category?
    suspend fun getAllCategories(): List<Category>
    suspend fun updateCategory(category: Category): Category?
    suspend fun deleteCategory(id: String): Boolean
}
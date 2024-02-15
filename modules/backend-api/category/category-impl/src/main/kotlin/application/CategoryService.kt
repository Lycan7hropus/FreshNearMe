package application

import presentation.dto.CategoriesDto
import presentation.dto.CategoryDto

internal interface CategoryService {
    suspend fun createCategory(categoryDto: CategoryDto): CategoryDto
    suspend fun deleteCategory(categoryId: String): Boolean
    suspend fun getCategories(): CategoriesDto
    suspend fun getCategory(id: String): CategoryDto
    suspend fun updateCategory()
}
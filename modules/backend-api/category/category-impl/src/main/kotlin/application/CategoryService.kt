package application

import domain.enitties.Category
import dto.CategoriesDto
import dto.CategoryDto

interface CategoryService {
    suspend fun createCategory(categoryDto: CategoryDto): CategoryDto
    suspend fun deleteCategory(categoryId: String): Boolean
    suspend fun getCategories(): CategoriesDto
    suspend fun getCategory(id: String): CategoryDto
    suspend fun updateCategory()
}
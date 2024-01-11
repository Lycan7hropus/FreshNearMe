package domain

import domain.enitties.Category
import presentation.dto.CategoriesDto
import presentation.dto.CategoryDto

interface CategoryService {
    suspend fun createCategory(categoryDto: CategoryDto): CategoriesDto
    suspend fun deleteCategory(categoryId: String): Result<Boolean>
    suspend fun getCategories(): CategoriesDto
    suspend fun getCategory(id: String): CategoriesDto
    suspend fun updateCategory(/* parameters */)
}
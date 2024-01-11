package domain

import domain.enitties.Category
import domain.enitties.CategoryFactory
import presentation.dto.CategoriesDto
import presentation.dto.CategoryDto

internal class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override suspend fun createCategory(categoryDto: CategoryDto): Category {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category)
    }

    override suspend fun deleteCategory(categoryId: String): Result<Boolean> {
        return try {
            val deletedCategory = categoryRepository.deleteCategory(categoryId)
            Result.success(deletedCategory)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategories(): CategoriesDto {
        val categories = categoryRepository.getAllCategories()
        return CategoriesDto(categories)
    }

    override suspend fun getCategory(id: String): Category {
        return categoryRepository.getCategoryById(id)
    }

    override suspend fun updateCategory(/* parameters */) {
        // Implementacja metody aktualizacji kategorii
    }
}
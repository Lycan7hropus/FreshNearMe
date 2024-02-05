package application

import domain.CategoryRepository
import domain.enitties.CategoryFactory
import dto.CategoriesDto
import dto.CategoryDto
import utils.transform

internal class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override suspend fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category).transform()
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
        return CategoriesDto(categories.map { it.transform() })
    }

    override suspend fun getCategory(id: String): CategoryDto {
        return categoryRepository.getCategoryById(id).transform()
    }

    override suspend fun updateCategory() {
        // Implementacja metody aktualizacji kategorii
    }
}


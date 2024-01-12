package application

import domain.CategoryRepository
import domain.enitties.Category
import domain.enitties.CategoryFactory
import dto.CategoriesDto
import dto.CategoryDto
import infra.toDto

internal class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override suspend fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category).toDto()
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
        return CategoriesDto(categories.map { it.toDto() })
    }

    override suspend fun getCategory(id: String): CategoryDto {
        return categoryRepository.getCategoryById(id).toDto()
    }

    override suspend fun updateCategory(/* parameters */) {
        // Implementacja metody aktualizacji kategorii
    }
}


package application

import domain.CategoryRepository
import domain.enitties.CategoryFactory
import dto.CategoriesDto
import dto.CategoryDto

internal class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override suspend fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category)  as CategoryDto
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
        return CategoriesDto(categories.map { it  as CategoryDto })
    }

    override suspend fun getCategory(id: String): CategoryDto {
        return categoryRepository.getCategoryById(id)  as CategoryDto
    }

    override suspend fun updateCategory() {
        // Implementacja metody aktualizacji kategorii
    }
}


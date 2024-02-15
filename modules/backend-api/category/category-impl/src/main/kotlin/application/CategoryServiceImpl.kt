package application

import domain.CategoryRepository
import domain.enitties.CategoryFactory
import presentation.dto.CategoriesDto
import presentation.dto.CategoryDto
import infra.toDto

internal class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    override suspend fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category).toDto()
    }

    override suspend fun deleteCategory(categoryId: String): Boolean {
        val isCategoryDeleted = categoryRepository.deleteCategory(categoryId)

        return isCategoryDeleted
    }

    override suspend fun getCategories(): CategoriesDto {
        val categories = categoryRepository.getAllCategories()
        return CategoriesDto(categories.map { it.toDto() })
    }

    override suspend fun getCategory(id: String): CategoryDto {
        return categoryRepository.getCategoryById(id).toDto()
    }

    override suspend fun updateCategory() {
        //TODO Implement the updateCategory
    }
}


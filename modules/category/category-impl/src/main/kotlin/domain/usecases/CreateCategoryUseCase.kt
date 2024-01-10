package domain.usecases

import domain.CategoryFactory
import domain.Category
import domain.CategoryRepository
import presentation.dto.CategoryDto

internal class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryDto: CategoryDto): Category {
        val categoryFactory = CategoryFactory(categoryRepository)
        val category = categoryFactory.createCategoryFromDTO(categoryDto)

        return categoryRepository.saveCategory(category)
    }
}
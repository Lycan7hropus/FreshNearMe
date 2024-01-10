package domain.usecases

import domain.Category
import domain.CategoryRepository


internal class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String): Category {
        return categoryRepository.getCategoryById(id)
    }
}
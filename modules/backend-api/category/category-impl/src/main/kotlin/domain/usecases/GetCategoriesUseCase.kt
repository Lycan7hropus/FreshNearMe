package domain.usecases

import domain.CategoryRepository
import presentation.dto.CategoriesDto

internal class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): CategoriesDto {
        val categories = categoryRepository.getAllCategories()
        return CategoriesDto(categories)
    }
}
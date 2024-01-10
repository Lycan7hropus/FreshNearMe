package domain.usecases

import domain.CategoryRepository


internal class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String): Result<Boolean> {
        return try {
            val deletedCategory = categoryRepository.deleteCategory(categoryId)
            Result.success(deletedCategory)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package domain.usecases

import com.example.features.category.domain.Category
import com.example.features.category.domain.CategoryRepository
class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String): Category {
        return categoryRepository.getCategoryById(id)
    }
}
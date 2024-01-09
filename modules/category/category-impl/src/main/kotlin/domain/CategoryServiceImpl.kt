package domain

import CategoryApi
import CategoryApiDto


class CategoryServiceImpl(private val categoryRepository: CategoryRepository) :
    CategoryApi {
    override suspend fun getCategoryById(id: String): CategoryApiDto {
        return categoryRepository.getCategoryById(id)
    }
}
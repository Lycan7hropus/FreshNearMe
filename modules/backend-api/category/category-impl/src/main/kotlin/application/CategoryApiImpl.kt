package application

import CategoryApi
import domain.CategoryRepository
import dto.CategoryApiDto
import infra.transform

internal class CategoryApiImpl(private val categoryRepository: CategoryRepository) :
    CategoryApi {
    override suspend fun getCategoryById(id: String): CategoryApiDto {
        return categoryRepository.getCategoryById(id).transform<CategoryApiDto>()
    }
}
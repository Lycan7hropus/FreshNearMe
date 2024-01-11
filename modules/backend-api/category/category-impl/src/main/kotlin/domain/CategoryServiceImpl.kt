package domain

import CategoryApi
import CategoryApiDto


internal class CategoryApiImpl(private val categoryRepository: CategoryRepository) :
    CategoryApi {
    override suspend fun getCategoryById(id: String): CategoryApiDto {
        return categoryRepository.getCategoryById(id).toCategoryApiDto()
    }
}

private fun Category.toCategoryApiDto(): CategoryApiDto {
    return CategoryApiDto(
        id = this.id,
        name = this.name,
        parentId = this.parentId,
        path = this.path
    )
}

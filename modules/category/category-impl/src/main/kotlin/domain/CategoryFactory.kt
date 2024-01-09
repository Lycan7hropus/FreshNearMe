package domain

import presentation.dto.CategoryDto
import java.util.*

class CategoryFactory(private val categoryRepository: CategoryRepository) {
    suspend fun createCategoryFromDTO(categoryDTO: CategoryDto): Category {
        val parentPath = categoryDTO.parentId?.let {
            categoryRepository.getCategoryById(it)?.path ?: ""
        } ?: ""

        val path = if (parentPath.isEmpty()) {
            "/${categoryDTO.name.replace(" ", "-")}"
        } else {
            "$parentPath/${categoryDTO.name.replace(" ", "-")}"
        }

        val newCategory = Category(
            id = UUID.randomUUID().toString(),
            name = categoryDTO.name,
            parentId = categoryDTO.parentId,
            path = path
        )

        return newCategory
    }
}

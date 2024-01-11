package presentation.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class CategoryDto(
    val parentId: String?,
    val name: String
)

package dto


data class CategoryApiDto(
    val id: String,
    val name: String,
    val parentId: String? = null,
    val myPath: String
)
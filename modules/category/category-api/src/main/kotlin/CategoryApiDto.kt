import kotlinx.serialization.Serializable

@Serializable
data class CategoryApiDto(
    val id: String,
    val name: String,
    val parentId: String? = null,
    val path: String
)
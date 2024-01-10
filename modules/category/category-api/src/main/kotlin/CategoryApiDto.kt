import org.bson.codecs.pojo.annotations.BsonId

data class CategoryApiDto(
    val id: String,
    val name: String,
    val parentId: String? = null,
    val path: String
)
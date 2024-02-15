package domain.enitties
import org.bson.codecs.pojo.annotations.BsonId


internal data class Category(
    @BsonId
    val id: String,
    val name: String,
    val parentId: String? = null,
    val path: String
)

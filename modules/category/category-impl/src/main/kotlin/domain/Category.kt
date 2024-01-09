package domain

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Category(
    @BsonId
    val id: String,
    val name: String,
    val parentId: String? = null,
    val path: String
)

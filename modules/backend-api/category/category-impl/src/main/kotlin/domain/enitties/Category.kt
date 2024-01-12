package domain.enitties


import dev.krud.shapeshift.enums.AutoMappingStrategy
import dev.krud.shapeshift.resolver.annotation.AutoMapping
import dto.CategoryDto
import org.bson.codecs.pojo.annotations.BsonId


@AutoMapping(CategoryDto::class, strategy = AutoMappingStrategy.BY_NAME)
internal data class Category(
    @BsonId
    val id: String,
    val name: String,
    val parentId: String? = null,
    val path: String
)

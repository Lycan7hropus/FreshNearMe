package domain.models


import com.example.OfferApiDto
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import utils.Role
import java.util.*

internal data class User(
    @BsonId
    val id: String = UUID.randomUUID().toString(),
    val role: Role,
    @SerialName("wish_list") val wishlist: List<OfferApiDto>,
    @SerialName("posted_offers") val postedOffers: List<OfferApiDto>,
) {
    fun toBasicDTO(): BasicUserDto {
        TODO("Not yet implemented")
    }

    fun toDetailedDTO(): DetailedUserDto {
        TODO("Not yet implemented")
    }
}
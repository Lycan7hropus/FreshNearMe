package domain

import com.example.OfferApiDto
import domain.models.User
import utils.Role

internal interface UserDataRepository {
    suspend fun getUser(userId: String): User
    suspend fun getUserWishList(userId: String): List<OfferApiDto>
    suspend fun updateUserWishlist(userId: String, list: List<OfferApiDto>): List<OfferApiDto>
    suspend fun getUserOffers(userId: String): List<OfferApiDto>
    suspend fun updateUser(user: User): Boolean
    suspend fun findUserByGoogleId(googleId: String): User
    suspend fun saveUser(user: User): User
    suspend fun getRole(userId: String): Role
    suspend fun updateUserOffers(userId: String, offers: List<OfferApiDto>): List<OfferApiDto>
}

package infra


import com.example.OfferApiDto
import domain.UserDataRepository
import com.mongodb.MongoWriteException
import domain.models.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo
import utils.Role
import utils.UserAlreadyExistsException
import utils.UserSavingException

internal class UserDataRepositoryImpl(private val usersCollection: CoroutineCollection<User>) : UserDataRepository {

    override suspend fun getUser(userId: String): User {
        return usersCollection.findOne(User::id eq userId)
            ?: throw IllegalArgumentException("User with ID $userId not found.")
    }

    override suspend fun getUserWishList(userId: String): List<OfferApiDto> {
        val user = getUser(userId)
        return user.wishlist
    }

    override suspend fun updateUserWishlist(userId: String, list: List<OfferApiDto>): List<OfferApiDto> {
        val updateResult = usersCollection.updateOne(User::id eq userId, set(User::wishlist setTo list))

        if (updateResult.matchedCount.toInt() == 0) {
            throw Exception("User not found")
        }

        return list
    }


    override suspend fun getUserOffers(userId: String): List<OfferApiDto> {
        val user = getUser(userId)
        return user.postedOffers
    }

    override suspend fun updateUser(user: User): Boolean {
        val result = usersCollection.updateOne(User::id eq user.id, user)
        return result.modifiedCount > 0
    }

    override suspend fun findUserByGoogleId(googleId: String): User {
        return usersCollection.findOne("{ 'googleInfo.id': '$googleId' }")
            ?: throw IllegalArgumentException("There is no any user associated with this google account")
    }


    override suspend fun saveUser(user: User): User {

        try {
            val result = usersCollection.insertOne(user)
            if (!result.wasAcknowledged()) {
                throw UserSavingException("Acknowledgement failed for user: ${user.id}")
            }
        } catch (e: MongoWriteException) {
            if (e.error.code == 11000) { // duplicate
                throw UserAlreadyExistsException("User with ID ${user.id} already exists.")
            } else {
                throw e
            }
        }

        return user
    }

    override suspend fun getRole(userId: String): Role {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserOffers(userId: String, offers: List<OfferApiDto>): List<OfferApiDto> {
        TODO("Not yet implemented")
    }
}

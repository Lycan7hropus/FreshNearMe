package com.example.features.user.data

import com.example.features.authentication.domain.model.UserGoogleInfo
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.models.Offer
import com.example.utils.exceptions.UserAlreadyExistsException
import com.example.utils.exceptions.UserSavingException
import com.mongodb.MongoWriteException
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class UserDataRepositoryImpl(private val usersCollection: CoroutineCollection<User>) : UserDataRepository {

    override suspend fun getUser(userId: String): User {
        return usersCollection.findOne(User::id eq userId) ?: throw IllegalArgumentException("User with ID $userId not found.")
    }

    override suspend fun getUserWishList(userId: String): List<Offer> {
        val user = getUser(userId)
        return user.wishlist
    }
    override suspend fun getUserOffers(userId: String): List<Offer> {
        val user = getUser(userId)
        return user.postedOffers
    }

    override suspend fun updateUser(user: User): Boolean {
        val result = usersCollection.updateOne(User::id eq user.id, user)
        return result.modifiedCount > 0
    }

    override suspend fun updateUserInfo(userInfo: UserGoogleInfo): User {
        val user = findUserByGoogleId(userInfo.id)
        val updatedUser = user.getUserWithUpdatedInfo(userInfo)
        return saveUser(updatedUser)
    }

    override suspend fun findUserByGoogleId(googleId: String): User {
        return usersCollection.findOne("{ 'googleInfo.id': '$googleId' }") ?: throw IllegalArgumentException("There is no any user associated with this google account")
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
}

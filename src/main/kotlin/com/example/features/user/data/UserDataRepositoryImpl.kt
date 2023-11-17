package com.example.features.user.data

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.UserRequest
import com.example.models.Offer
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

    override suspend fun updateUserInfo(userInfo: UserRequest): Boolean {
//        val user = getUser(userInfo.id) ?: return false
//        user.updateInfo(userInfo)
        return false
    }

    override suspend fun saveUser(userInfo: UserRequest): Boolean {
        val user = User(userInfo = userInfo)
        val result = usersCollection.insertOne(user)
        return result.wasAcknowledged()
    }
}

package com.example.features.user.data

import com.example.features.authentication.domain.model.GoogleUserInfo
import com.example.features.offer.domain.Offer
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import com.example.utils.Role
import org.junit.jupiter.api.Assertions.*

class FakeUserRepository: UserDataRepository {

    override suspend fun getUser(userId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWishList(userId: String): List<Offer> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserWishlist(userId: String, list: List<Offer>): List<Offer> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserOffers(userId: String): List<Offer> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserInfo(userInfo: GoogleUserInfo): User {
        TODO("Not yet implemented")
    }

    override suspend fun findUserByGoogleId(googleId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User): User {
        TODO("Not yet implemented")
    }

    override suspend fun getRole(userId: String): Role {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserOffers(userId: String, offers: List<Offer>): List<Offer> {
        TODO("Not yet implemented")
    }
}
package com.example.features.auth.data

import com.example.features.auth.domain.AuthRepository
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.models.User
import org.litote.kmongo.coroutine.CoroutineCollection

class AuthRepositoryImpl(private val usersCollection: CoroutineCollection<User>): AuthRepository {

}
package com.example.features.auth.domain

import com.example.features.user.domain.models.User

interface AuthRepository {
    fun saveUser(user: User):User

    fun updateUser(user: User):User
}
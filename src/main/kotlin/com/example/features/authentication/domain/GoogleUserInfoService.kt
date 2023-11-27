package com.example.features.authentication.domain

import com.example.features.authentication.domain.model.UserGoogleInfo

interface GoogleUserInfoService {
    suspend fun getUserInfo(token: String): UserGoogleInfo
}
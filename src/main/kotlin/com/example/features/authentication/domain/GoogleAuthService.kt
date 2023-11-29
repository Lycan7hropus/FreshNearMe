package com.example.features.authentication.domain

import com.example.features.authentication.domain.model.GoogleUserInfo

interface GoogleAuthService {
    suspend fun getUserInfo(token: String): GoogleUserInfo
    suspend fun verifyGoogleToken(accessToken: String): String
}
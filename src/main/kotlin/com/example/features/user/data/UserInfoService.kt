package com.example.features.user.data

import com.example.features.user.domain.models.UserGoogleInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class GoogleUserInfoService(private val httpClient: HttpClient) {
    suspend fun getUserInfo(token: String): UserGoogleInfo {
        val userInfoJson: String = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
            headers { append(HttpHeaders.Authorization, "Bearer $token") }
        }.body<String>()

        return parseUserInfo(userInfoJson)
    }

    private fun parseUserInfo(json: String): UserGoogleInfo {
        // Implementacja parsera JSON, np. używając kotlinx.serialization lub Gson
        return  UserGoogleInfo("mock")
    }
}
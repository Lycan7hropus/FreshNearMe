package com.example.features.authentication.data

import com.example.features.authentication.domain.GoogleUserInfoService
import com.example.features.authentication.domain.model.UserGoogleInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class GoogleUserInfoServiceImpl(private val httpClient: HttpClient) : GoogleUserInfoService {
    override suspend fun getUserInfo(token: String): UserGoogleInfo {
        val userInfo: String = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
            headers { append(HttpHeaders.Authorization, "Bearer $token") }
        }.body()

        return Json.decodeFromString(userInfo)
    }
}
package com.example.features.authentication.data

import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.authentication.domain.model.GoogleTokenError
import com.example.features.authentication.domain.model.GoogleTokenInfo
import com.example.features.authentication.domain.model.GoogleUserInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.naming.AuthenticationException

class GoogleAuthServiceImpl(private val httpClient: HttpClient) : GoogleAuthService {
    override suspend fun getUserInfo(token: String): GoogleUserInfo {
        val userInfo: String = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
            headers { append(HttpHeaders.Authorization, "Bearer $token") }
        }.body()

        return Json.decodeFromString(userInfo)
    }

    override suspend fun verifyGoogleToken(accessToken: String): String {
        val response: HttpResponse = httpClient.get("https://www.googleapis.com/oauth2/v1/tokeninfo") {
            parameter("access_token", accessToken)
        }
        val responseBody = response.bodyAsText()

        return when (response.status) {
            HttpStatusCode.OK -> {
                val tokenInfo = Json.decodeFromString<GoogleTokenInfo>(responseBody)
                if (tokenInfo.audience == System.getenv("GOOGLE_CLIENT_ID") && tokenInfo.expiresIn > 0) {
                    tokenInfo.userId
                } else {
                    //TODO RETURN THIS THROW
                    //throw AuthenticationException("Invalid token: audience mismatch or token expired")
                    tokenInfo.userId
                }
            }

            else -> {
                val errorResponse = Json.decodeFromString<GoogleTokenError>(responseBody)
                throw AuthenticationException("Authentication failed because: ${errorResponse.error}")
            }
        }
    }
}
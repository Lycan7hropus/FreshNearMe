package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.InvalidClaimException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun Application.configureSecurity() {
    install(Authentication) {
        bearer("auth-bearer") {
            realm = "Access to the '/' path"
            authenticate { tokenCredential ->
                try {
                    val userId = verifyGoogleToken(tokenCredential.token)
                    if (userId != null) {
                        UserIdPrincipal(userId)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    println("Błąd podczas weryfikacji tokenu: ${e.message}")
                    null
                }
            }
        }
    }
}
suspend fun verifyGoogleToken(accessToken: String): String? {
    val httpClient = HttpClient()
    return try {
        val response: HttpResponse = httpClient.get("https://www.googleapis.com/oauth2/v1/tokeninfo") {
            parameter("access_token", accessToken)
        }
        val responseBody = response.bodyAsText()

        val tokenInfo = Json.decodeFromString<GoogleTokenInfo>(responseBody)

        if (tokenInfo.audience == System.getenv("GOOGLE_CLIENT_ID") && tokenInfo.expiresIn > 0) {
            tokenInfo.userId
        } else {
            null
        }
    } catch (e: Exception) {
        throw Exception("Weryfikacja tokenu nie powiodła się: ${e.message}", e)
    } finally {
        httpClient.close()
    }
}
@Serializable
data class GoogleTokenInfo(
    @SerialName("issued_to") val issuedTo: String,
    @SerialName("audience") val audience: String,
    @SerialName("user_id") val userId: String,
    @SerialName("scope") val scope: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("access_type") val accessType: String
)
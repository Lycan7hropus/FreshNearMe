package com.example.plugins

import com.example.features.user.domain.UserDataRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import javax.naming.AuthenticationException

fun Application.configureSecurity() {
    val googleAuthenticationService: GoogleAuthenticationService by inject()

    install(Authentication) {
        bearer("auth-bearer") {
            realm = "Access to the '/' path"
            authenticate { tokenCredential ->
                val httpClient: HttpClient by inject()
                val googleId = googleAuthenticationService.verifyGoogleToken(tokenCredential.token)
                val userId = googleAuthenticationService.getUserId(googleId)
                UserIdPrincipal(userId)
            }
        }
    }
}

class GoogleAuthenticationService(
    private val httpClient: HttpClient,
    private val userDataRepository: UserDataRepository
) {

    suspend fun getUserId(googleId: String): String {
        return userDataRepository.findUserByGoogleId(googleId).id
    }

    suspend fun verifyGoogleToken(accessToken: String): String {
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
                    throw AuthenticationException("Invalid token: audience mismatch or token expired")
                }
            }

            else -> {
                val errorResponse = Json.decodeFromString<GoogleTokenError>(responseBody)
                throw AuthenticationException("Authentication failed because: ${errorResponse.error}")
            }
        }
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

@Serializable
data class GoogleTokenError(
    val error: String,
    @SerialName("error_description") val errorDescription: String
)

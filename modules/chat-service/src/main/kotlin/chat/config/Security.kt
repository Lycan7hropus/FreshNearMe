package com.example.chat.config

import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWTVerifier
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.mockk.mockk
import utils.Role
import utils.extensionFunctions.toJwtUserPrincipal
import utils.models.JwtUserPrincipal
import java.net.URL
import javax.naming.AuthenticationException

fun Application.configureSecurity() {
    val myRealm = "fresh-near-me"
    val port = "18080"
    val issuer = "http://localhost:$port/realms/$myRealm"
    val client = "fresh-near-me-client"
    val audience = "account"
    val jwkProvider = "http://localhost:$port/realms/$myRealm/protocol/openid-connect/certs"


    install(Authentication) {
//        jwt("auth-jwt") {
//            realm = myRealm
//            verifier(
//                UrlJwkProvider(URL(jwkProvider)),
//                issuer
//            ) {
//                withAudience(audience)
//                withClaim("azp", client)
//            }
//            challenge { _, _ ->
//
//                throw AuthenticationException("Token is not valid or has expired")
//            }
//            validate { credential ->
//                credential.toJwtUserPrincipal()
//            }
//        }

        bearer("auth-jwt") {
            realm = "TestRealm"
            authenticate {
                JwtUserPrincipal(
                    exp = null,
                    roles = listOf(Role.ADMIN), // Możesz dostosować role zgodnie z potrzebami testu
                    email = "test@example.com",
                    familyName = "TestFamilyName",
                    givenName = "TestGivenName",
                    preferredUsername = "testUser",
                    name = "Test Name",
                    emailVerified = true,
                    sid = "testSessionId",
                    sub = "${it.token}_id"
                )
            }
        }
    }
}



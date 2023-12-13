package com.example.features.user.presentation

import com.example.BaseRoutingTest
import com.example.di.appModule
import com.example.di.features.*
import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.user.domain.UserService
import com.example.models.UserPrincipal
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPages
import com.example.services.FakeUserDtoService
import com.example.utils.Role
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.ktor.ext.getKoin
import org.koin.test.inject


class UserRoutesTest : BaseRoutingTest() {
    val fakeUser = FakeUserDtoService().randomBasicUserDto()
    val fakeDetailedUser = FakeUserDtoService().randomDetailedUserDto()
    val userId = fakeUser.id
    private val token = "token"
    private val mockGoogleAuthService: GoogleAuthService by inject()
    private val userService: UserService by inject()


    override fun implementModules(): Application.() -> Unit {
        return {
            configureTestSecurity()
            configureSerialization()
            configureStatusPages()
            configureRouting()
        }
    }


    @BeforeEach
    fun setUp() {
        stopKoin()
        startKoin {
            modules(listOf(appModule, testDatabaseModule, categoryModule, offerModule, testUserModule, testAuthModule))
        }

    }

    @Test
    fun `GET user by userId returns basic user info`() = baseRouteTestApplication() {
        this.client.post("/auth/sync")

        this.client.get("/user/$userId").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }

    @Test
    fun `GET user by userId returns detailed user info`() = baseRouteTestApplication() {
        this.client.get("/user/my_info"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }


    @Test
    fun `GET user offers by user id`() = baseRouteTestApplication {
        this.client.get("/user/$userId/offers").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }
    private fun Application.configureTestSecurity(googleAuthenticationService: GoogleAuthService = getKoin().get(), userService: UserService = getKoin().get()) {
        install(Authentication) {
            bearer("auth-bearer") {
                realm = "Test realm"
                authenticate { tokenCredential ->
                    coEvery { mockGoogleAuthService.verifyGoogleToken(any())} returns "mockedGoogleId"
                    coEvery { userService.findUserByGoogleId(tokenCredential.token) } returns mockk()
                    coEvery { userService.getRole(tokenCredential.token) } returns Role.USER


                    val googleId = googleAuthenticationService.verifyGoogleToken(tokenCredential.token)
                    val userId = userService.findUserByGoogleId(googleId).id
                    val userRole = userService.getRole(userId)
                    UserPrincipal(userId, userRole)
                }
            }
        }
    }


}


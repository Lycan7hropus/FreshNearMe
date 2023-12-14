package com.example.features.user.presentation

import com.example.BaseRoutingTest
import com.example.di.appModule
import com.example.di.databaseModule
import com.example.di.features.*
import com.example.features.authentication.domain.GoogleAuthService
import com.example.features.user.domain.UserService
import com.example.models.UserPrincipal
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
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
    private val token = "ya29.a0AfB_byC3YSphxEGumNHPHp42zsa1cGDD_Bo8TcfgQZvzbL4pYFf6iivl73VTV49NT9qte7MboxhUusuZOG34khLuqJyyAJsBq7P3rt094l3tgRJE4m3yH3j4p2gsKAx1AzN0MqKCr3QFTKXY7ggz2Fek9wY2dJKXFnLmaCgYKARYSARMSFQHGX2MiTK_FeSI-vntGs8lQvPy-_Q0171"
    private val mockGoogleAuthService: GoogleAuthService by inject()
    private val userService: UserService by inject()

    //correct_data 200
    //missing_data 404
    //bad_data 500

    override fun implementModules(): Application.() -> Unit {
        return {
            configureSecurity()
            configureSerialization()
            configureStatusPages()
            configureRouting()
        }
    }

    @BeforeEach
    fun setUp() {
        stopKoin()
        startKoin {
            //modules(listOf(appModule, testDatabaseModule, categoryModule, offerModule, testUserModule, testAuthModule))
            modules(listOf(appModule, databaseModule, categoryModule, offerModule, userModule, authModule))
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


}


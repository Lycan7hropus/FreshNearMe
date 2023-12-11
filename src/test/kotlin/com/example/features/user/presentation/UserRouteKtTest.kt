package com.example.features.user.presentation

import com.example.di.appModule
import com.example.di.databaseModule
import com.example.di.features.authModule
import com.example.di.features.categoryModule
import com.example.di.features.offerModule
import com.example.di.features.testUserModule
import com.example.features.user.domain.usecases.GetUserInfoUseCase
import com.example.features.user.domain.usecases.GetUserOffersUseCase
import com.example.features.user.presentation.models.PostedOffersDto
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPages
import com.example.services.MockUserDtoService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class UserRoutesTest : KoinTest {

    private val getUserInfoUseCase: GetUserInfoUseCase by inject()
    private val getUserOffersUseCase: GetUserOffersUseCase by inject()
    val fakeUser = MockUserDtoService().randomBasicUserDto()
    val userId = fakeUser.id


    @BeforeEach
    fun setUp() {
        stopKoin()
        startKoin {
            modules(listOf(appModule, databaseModule, categoryModule, offerModule, testUserModule, authModule))
        }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun `GET user by userId returns user info`() = baseUserRouteTestApplication {
        coEvery {
            getUserInfoUseCase.getBasicInfo(any())
        } returns fakeUser

        this.client.get("/user/$userId").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }

    @Test
    fun `GET user offers by user id`() = baseUserRouteTestApplication {
        coEvery {
            getUserOffersUseCase(any())
        } returns PostedOffersDto(fakeUser.postedOffers)

        this.client.get("/user/$userId/offers").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }

    private fun baseUserRouteTestApplication(block: suspend ApplicationTestBuilder.() -> Unit) {
        testApplication() {
            environment {
                config = MapApplicationConfig("ktor.environment" to "dev")
            }
            application {
                configureSecurity()
                configureSerialization()
                configureStatusPages()
                configureRouting()
            }
            block()
        }
    }
}


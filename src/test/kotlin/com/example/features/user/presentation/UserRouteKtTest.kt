package com.example.features.user.presentation

import com.example.di.appModule
import com.example.di.databaseModule
import com.example.di.features.*
import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.models.PostedOffersDto
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPages
import com.example.services.MockUserDtoService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.routing.*
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

class UserRoutesTest: KoinTest {

    private val getUserInfoUseCase: GetUserInfoUseCase by inject()
    private val getUserOffersUseCase: GetUserOffersUseCase by inject()
    val fakeUser = MockUserDtoService().randomBasicUserDto()
    val userId = fakeUser.id


    @BeforeEach
    fun setUp() {
        stopKoin() // Ensure no previous Koin instance is running

    }

    @AfterEach
    fun tearDown() {
        stopKoin() // Zatrzymaj Koin po każdym teście
    }


    @Test
    fun `GET user by userId returns user info`() = testApplication {
        environment {
            config = MapApplicationConfig("ktor.environment" to "dev")
        }
        application {
            configure()  // Make sure to include this
            coEvery {
                getUserInfoUseCase.getBasicInfo(any())
            } returns fakeUser
        }

        this.client.get("/user/$userId").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }

    @Test
    fun `GET user offers by user id`() = testApplication {
        environment {
            config = MapApplicationConfig("ktor.environment" to "dev")
        }
        application {
            configure()  // Make sure to include this
            coEvery {
                getUserOffersUseCase(any())
            } returns PostedOffersDto(fakeUser.postedOffers)
        }

        this.client.get("/user/$userId/offers").apply {
            val responseBody = bodyAsText()
            assertEquals(HttpStatusCode.OK, status)
            assertNotNull(responseBody)
        }
    }

    private fun Application.configure() {
        startKoin {
            modules(listOf(appModule, databaseModule, categoryModule, offerModule, testUserModule, authModule))
        }
        configureSecurity()
        configureSerialization()
        configureStatusPages()
        this.routing {
            userRoutes()
        }
    }
}
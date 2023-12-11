package com.example.features.user.presentation

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.domain.usecases.*
import com.example.services.MockUserDtoService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class UserRoutesTest: KoinTest {

    private val getUserInfoUseCase: GetUserInfoUseCase by inject()
    val fakeUser = MockUserDtoService().randomBasicUserDto()
    val userId = fakeUser.id


    val userTestModule = module {
        // Mocking the UserDataRepository
        single<UserDataRepository> { mockk(relaxed = true) }

        // Mocking each use case with MockK
        single { mockk<GetUserOffersUseCase>(relaxed = true) }
        single { mockk<GetUserInfoUseCase>(relaxed = true) }
        single { mockk<UserWishlistUseCase>(relaxed = true) }
        single { mockk<SaveUserUseCase>(relaxed = true) }
        single { mockk<UpdateUserDataUseCase>(relaxed = true) }
    }

    @BeforeEach
    fun setUp() {
        stopKoin() // Ensure no previous Koin instance is running
        startKoin {
            modules(userTestModule) // Load your test module here
        }
        coEvery { getUserInfoUseCase.getBasicInfo(any()) } returns fakeUser
    }

    @AfterEach
    fun tearDown() {
        stopKoin() // Zatrzymaj Koin po każdym teście
    }


    @Test
    fun `GET user by userId returns user info`() = testApplication {
        application {
            configureRouting()  // Make sure to include this
        }

        this.client.get("/user/$userId").apply {
            assertEquals(HttpStatusCode.OK, status)
            val responseBody = bodyAsText()
            assertNotNull(responseBody)
        }
    }

    private fun Application.configureRouting() {
        this.routing {
            userRoutes(getUserInfoUseCase = getUserInfoUseCase)
        }
    }
}
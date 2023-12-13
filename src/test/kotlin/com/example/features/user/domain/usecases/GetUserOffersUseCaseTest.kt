package com.example.features.user.domain.usecases

import com.example.di.appModule
import com.example.di.features.*
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDto
import com.example.services.FakeUserDtoService
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject


class GetUserOffersUseCaseTest: KoinTest {


    private var userRepository: UserDataRepository = mockk()
    private val fakeUserDtoService: FakeUserDtoService by inject()
    private lateinit var getUserOffersUseCase: GetUserOffersUseCase

    @BeforeEach
    fun setup() {
        stopKoin()
        startKoin {
            modules(listOf(appModule, testDatabaseModule, categoryModule, offerModule, testUserModule, testAuthModule))
        }
        getUserOffersUseCase = GetUserOffersUseCase(userRepository)
    }

    @Test
    fun `can get User offers`() = runBlocking {
        // Arrange
        val userId = "123"

        val userIdSlot = slot<String>()

        val offers = fakeUserDtoService.randomUser().postedOffers
        coEvery { userRepository.getUserOffers(userId)} returns offers

        // Act
        val result = getUserOffersUseCase(userId)

        coVerify(exactly = 1) { userRepository.getUserOffers(capture(userIdSlot)) }


        assertEquals(userId, userIdSlot.captured)

        // Assert
        assertEquals(PostedOffersDto(offers), result)
    }

    @Test
    fun `invoke should return PostedOffersDto`() = runBlocking {
        // Arrange
        val userId = "123"
        val offers = fakeUserDtoService.randomUser().postedOffers
        coEvery { userRepository.getUserOffers(userId)} returns offers

        // Act
        val result = getUserOffersUseCase(userId)

        // Assert
        assertEquals(PostedOffersDto(offers), result)
    }
}
package com.example.features.user.domain.usecases

import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.PostedOffersDto
import com.example.services.MockOfferService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking

class GetUserOffersUseCaseTest : FunSpec({

    val userRepository = mockk<UserDataRepository>(relaxed = true)
    val offerService = MockOfferService()

    test("invoke should return PostedOffersDto with offers") {
        val userId = "testUserId"
        val fakeOffers = List(10) { offerService.randomOffer() }
        coEvery { userRepository.getUserOffers(userId) } returns fakeOffers

        val getUserOffersUseCase = GetUserOffersUseCase(userRepository)

        runBlocking {
            val result = getUserOffersUseCase(userId)
            result shouldBe PostedOffersDto(fakeOffers)
        }
    }
})

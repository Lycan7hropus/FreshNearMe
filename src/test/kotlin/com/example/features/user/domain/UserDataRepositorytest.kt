package com.example.features.user.domain

import com.example.di.appModule
import com.example.di.features.*
import com.example.services.FakeOfferService
import com.example.services.FakeUserDtoService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject


class UserDataRepositoryTest: KoinTest {
    private val underTest: UserDataRepository by inject()
    private val fakeOffersService: FakeOfferService by inject()
    private val fakeUserService: FakeUserDtoService by inject()

    @BeforeEach
    fun setUp() {
        stopKoin()
        startKoin {
            modules(listOf(appModule, testDatabaseModule, categoryModule, offerModule, testUserModule, testAuthModule))
        }
    }

    @Test
    fun `should get user by id`() {
        //given
        val userId = "123"
        //when
        val user = runBlocking { underTest.getUser(userId) }

        //then
        assertEquals(userId, user.id)
    }

    @Test
    fun `should get user wishlist by id`() {
        runBlocking {
            val userId = "123"
            val wishlist = underTest.getUserWishList(userId)
            // Assert the expected wishlist items or conditions
        }
    }

    @Test
    fun `should update user wishlist`() {
        runBlocking {
            val userId = "123"
            val newWishlist = fakeUserService.randomUser().wishlist
            val updatedWishlist = underTest.updateUserWishlist(userId, newWishlist)
            // Assert the updated wishlist items or conditions
        }
    }

    @Test
    fun `should get user offers by id`() {
        runBlocking {
            val userId = "123"
            val offers = underTest.getUserOffers(userId)
            // Assert the expected offers or conditions
        }
    }

    @Test
    fun `should update user`() {
        runBlocking {
            val user = fakeUserService.randomUser()
            val isUpdated = underTest.updateUser(user)
            assertEquals(true, isUpdated)
        }
    }

//    @Test
//    fun `should update user info`() {
//        runBlocking {
//            val userInfo = GoogleUserInfo("123", "John Doe", "john.doe@example.com")
//            val updatedUser = underTest.updateUserInfo(userInfo)
//            // Assert the updated user or conditions
//        }
//    }

    @Test
    fun `should find user by Google ID`() {
        runBlocking {
            val googleId = "123"
            val user = underTest.findUserByGoogleId(googleId)
            // Assert the expected user or conditions
        }
    }

    @Test
    fun `should save user`() {
        runBlocking {
            val user = fakeUserService.randomUser()
            val savedUser = underTest.saveUser(user)
            // Assert the saved user or conditions
        }
    }

    @Test
    fun `should get user role by id`() {
        runBlocking {
            val userId = "123"
            val role = underTest.getRole(userId)
            // Assert the expected role or conditions
        }
    }

    @Test
    fun `should update user offers`() {
        runBlocking {
            val userId = "123"
            val newOffers = fakeUserService.randomUser().postedOffers
            val updatedOffers = underTest.updateUserOffers(userId, newOffers)
        }
    }
}
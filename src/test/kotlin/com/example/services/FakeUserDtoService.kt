package com.example.services

import com.example.features.authentication.domain.model.GoogleUserInfo
import com.example.features.user.domain.models.User
import com.example.features.user.presentation.models.BasicUserDto
import com.example.features.user.presentation.models.DetailedUserDto
import com.example.utils.Role
import io.mockk.mockk
import net.datafaker.Faker
import java.util.UUID

class FakeUserDtoService {
    val faker = Faker()
    val fakeOfferService = FakeOfferService()
    fun randomBasicUserDto(): BasicUserDto{
        val id = UUID.randomUUID().toString()
        val offers = List(10){ fakeOfferService.randomOffer(id) }
        return BasicUserDto(
            id = id,
            postedOffers = offers,
            givenName = faker.funnyName().name(),
            picture = faker.internet().image()
        )
    }

    fun randomDetailedUserDto(): DetailedUserDto{
        val id = UUID.randomUUID().toString()
        val offers = List(10){ fakeOfferService.randomOffer(id) }
        val wishlist = List(5){ fakeOfferService.randomOffer(id) }
        return DetailedUserDto(
            id = id,
            wishlist = offers,
            postedOffers = wishlist,
            name = faker.name().firstName(),
            givenName = faker.name().fullName(),
            familyName = faker.name().lastName(),
            picture = faker.internet().image(),
            locale = "en"
        )
    }

    fun randomUser(): User {
        val id = UUID.randomUUID().toString()
        val offers = List(10){ fakeOfferService.randomOffer(id) }
        val wishlist = List(5){ fakeOfferService.randomOffer(id) }
        return User(
            id = id,
            role = Role.USER,
            wishlist = offers,
            postedOffers = wishlist,
            googleInfo = mockk<GoogleUserInfo>()
        )
    }
}
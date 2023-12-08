package com.example.services

import com.example.features.user.presentation.models.BasicUserDto
import net.datafaker.Faker
import java.util.UUID

class MockUserDtoService {
    val faker = Faker()
    val userService = MockOfferService()
    fun randomBasicUserDto(): BasicUserDto{
        val id = UUID.randomUUID().toString()
        val offers = List(10){ userService.randomOffer(id) }
        return BasicUserDto(
            id = id,
            postedOffers = offers,
            givenName = faker.funnyName().name(),
            picture = faker.internet().image()
        )
    }
}
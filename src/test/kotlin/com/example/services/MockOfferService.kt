package com.example.services

import com.example.features.category.domain.Category
import com.example.features.offer.domain.Offer
import net.datafaker.Faker
import java.util.*
import kotlin.random.Random

class MockOfferService {

    val localDateTimeService = LocalDateTimeService()
    val randomGeoPointService = GeoPointService()
    val categoryService = MockCategoryService()
    val faker = Faker()

    fun randomBeer(sellerId: String): Offer{
        val beer =  faker.beer()
        return Offer(
            id = UUID.randomUUID().toString(),
            name = beer.name(),
            category = categoryService.getCategory("beer"),
            price = Random.nextDouble(1.0, 12.0),
            phoneNumber = faker.phoneNumber().phoneNumber(),
            description = "${beer.name()} is a ${beer.style()} beer, made with ${beer.yeast()} yests",
            imageUrl = null,
            postedTime = localDateTimeService.randomLocalDateTime(),
            sellerId = sellerId,
            geoPoint = randomGeoPointService.randomGeoPointNearWarsaw(),
            isActive = Random.nextBoolean()
        )
    }

    fun randomOffer(sellerId: String = UUID.randomUUID().toString()): Offer {
        return randomBeer(sellerId)
    }

}
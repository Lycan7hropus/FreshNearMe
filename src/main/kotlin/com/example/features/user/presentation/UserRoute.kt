package com.example.features.user.presentation

import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.dto.OfferDto
import com.example.models.Coordinates
import com.example.utils.exceptions.OfferCreationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    // Route for getting a user's wishlist
    get("/user/{userId}/wishlist") {
        // Handle fetching a user's wishlist here
    }


    // Route for getting a list of offers posted by a specific user
    get("/user/{userId}/offers") {
        // Handle fetching offers for a specific user here
    }


    // Route for getting user information by ID
    get("/user/{userId}") {
        // Handle fetching user information here
    }

}

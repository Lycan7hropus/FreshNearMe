package com.example.features.offer.presentation

import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.dto.OfferDto
import com.example.models.Coordinates
import com.example.utils.extensionFunctions.getUserId
import com.example.utils.extensionFunctions.respondSuccess
import com.example.utils.extensionFunctions.toCoordinates
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.offerRoutes(
    createOfferUseCase: CreateOfferUseCase,
    getAllOffersUseCase: GetOffersUseCase,
    getOfferByIdUseCase: GetOfferByIdUseCase,
    updateOfferUseCase: UpdateOfferUseCase
) {
    // Route for getting a list of all offers
    route("/offers"){
        get() {
            val category = call.request.queryParameters["category"]
            val coordinates = call.request.queryParameters["coordinates"]?.toCoordinates()
            val distance = call.request.queryParameters["distance"]?.toDoubleOrNull()

            val offersDto = getAllOffersUseCase(category, distance, coordinates)
            call.respondSuccess(offersDto)
        }

        // Route for getting a single offer by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
            val offerDto = getOfferByIdUseCase(id)
            call.respondSuccess(offerDto)
        }

        // Route for adding an offer
        authenticate("auth-bearer") {
            post() {
                val newOfferDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val createdOffer = createOfferUseCase(newOfferDto, userId)
                call.respondSuccess(createdOffer)
            }

            put("/{id}") {
                val offerId = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
                val offerToUpdateDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val offer = updateOfferUseCase(offerId, userId, offerToUpdateDto)

                call.respondSuccess(offer)
            }
        }
    }
}


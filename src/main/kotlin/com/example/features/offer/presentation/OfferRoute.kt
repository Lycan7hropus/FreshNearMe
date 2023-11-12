package com.example.features.offer.presentation

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

fun Route.offerRoutes() {
    val createOfferUseCase: CreateOfferUseCase by inject()
    val getAllOffersUseCase: GetOffersUseCase by inject()
    val getOfferByIdUseCase: GetOfferByIdUseCase by inject()
    val updateOfferUseCase: UpdateOfferUseCase by inject()

    // Route for getting a list of all offers
    get("/offers") {
        val category = call.request.queryParameters["category"]
        val coordinates = call.request.queryParameters["coordinates"]?.toCoordinates()
        val distance = call.request.queryParameters["distance"]?.toDoubleOrNull()

        val result = getAllOffersUseCase.invoke(category, distance, coordinates)

        result.fold(
            onSuccess = { offers ->
                call.respond(HttpStatusCode.OK, offers)
            },
            onFailure = { exception ->
                call.respond(HttpStatusCode.InternalServerError, exception.message ?: "Error fetching offers")
            }
        )
    }
    // Route for adding an offer
    post("/offers") {
        val newOfferDto = call.receive<OfferDto>()
        val result = createOfferUseCase(newOfferDto)

        result.fold(
            onSuccess = { createdOffer ->
                call.respond(HttpStatusCode.Created, createdOffer)
            },
            onFailure = { exception ->
                when (exception) {
                    is IllegalArgumentException -> call.respond(HttpStatusCode.BadRequest, exception.message ?: "Invalid offer data")
                    is OfferCreationException -> call.respond(HttpStatusCode.InternalServerError, exception.message ?: "Could not create offer")
                    else -> call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
                }
            }
        )
    }

    // Route for getting a single offer by ID
    get("/offers/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing offer ID")
        val result = getOfferByIdUseCase(id)
        result.fold(
            onSuccess = { offerDto ->
                call.respond(HttpStatusCode.Created, offerDto)
            },
            onFailure = { exception ->
                when (exception) {
                    is NotFoundException -> call.respond(HttpStatusCode.NotFound, exception.message ?: "Offer not found")
                    else -> call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
                }
            }
        )
    }

    put("/offers/{id}") {
        val offerId = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing offer ID")
        val offerToUpdateDto = call.receive<OfferDto>()

        val result = updateOfferUseCase(offerId, offerToUpdateDto)
        result.fold(
            onSuccess = { updatedOffer ->
                call.respond(HttpStatusCode.OK, updatedOffer) // Use OK status
            },
            onFailure = { exception ->
                when (exception) {
                    is IllegalArgumentException -> call.respond(HttpStatusCode.BadRequest, exception.message ?: "Invalid offer data")
                    else -> call.respond(HttpStatusCode.InternalServerError, exception.message ?: "An unexpected error occurred")
                }
            }
        )
    }

}

fun String.toCoordinates(): Coordinates? {
    val parts = this.split(',')
    if (parts.size == 2) {
        val latitude = parts[0].toDoubleOrNull()
        val longitude = parts[1].toDoubleOrNull()
        if (latitude != null && longitude != null) {
            return Coordinates(latitude, longitude)
        }
    }
    return null
}

package com.example.features.rating.presentation

import com.example.features.rating.domain.RatingService
import com.example.features.rating.domain.models.Rating
import com.example.utils.extensionFunctions.getUserId
import com.example.utils.extensionFunctions.respondSuccess
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.ratingRoutes(ratingService: RatingService) {

    route("ratings"){

        get("/by-user/{userId}") {
            val userId = call.parameters["userId"]  ?: throw MissingRequestParameterException("userId")
            val ratings = ratingService.getRatingsForUser(userId)
            call.respondSuccess(ratings)
        }

        get("/by-id/{ratingId}") {
            val ratingId = call.parameters["ratingId"]  ?: throw MissingRequestParameterException("ratingId")
            //TODO
        }

        authenticate {
            post() {
                val rating = call.receive<Rating>()
                val addedRating = ratingService.addRating(rating)
                call.respondSuccess(addedRating)
            }

            put("/by-id/{ratingId}") {
               //TODO
            }



            get("/given") {
                val userId = call.getUserId()
                val givenRatings = ratingService.getGivenRatingsByUser(userId)
                call.respondSuccess(givenRatings)
            }
        }
    }

}

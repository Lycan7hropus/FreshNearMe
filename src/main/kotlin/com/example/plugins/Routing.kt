package com.example.plugins

import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.features.user.presentation.userRoutes
import com.example.utils.extensionFunctions.respondSuccess
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val createOfferUseCase: CreateOfferUseCase by inject()
        val getAllOffersUseCase: GetOffersUseCase by inject()
        val getOfferByIdUseCase: GetOfferByIdUseCase by inject()
        val updateOfferUseCase: UpdateOfferUseCase by inject()
        offerRoutes(
            createOfferUseCase,
            getAllOffersUseCase,
            getOfferByIdUseCase,
            updateOfferUseCase
        )

        categoryRoutes()
        userRoutes()

        get("/hello_world"){

            call.respondSuccess("Hello world", HttpStatusCode.OK)
        }
    }
}

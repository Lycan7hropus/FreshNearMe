package com.example.plugins

import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.category.domain.usecases.GetCategoriesUseCase
import com.example.features.category.domain.usecases.GetCategoryUseCase
import com.example.features.offer.domain.usecases.*
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.features.rating.domain.RatingService
import com.example.features.rating.presentation.ratingRoutes
import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.userRoutes
import com.example.models.JwtUserPrincipal
import com.example.utils.Role
import com.example.utils.extensionFunctions.respondSuccess
import com.example.utils.extensionFunctions.withRole
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        val createOfferUseCase: CreateOfferUseCase by inject()
        val getAllOffersUseCase: GetOffersUseCase by inject()
        val getOfferByIdUseCase: GetOfferByIdUseCase by inject()
        val updateOfferUseCase: UpdateOfferUseCase by inject()
        val getOffersByNameUseCase: GetOffersByNameUseCase by inject()
        offerRoutes(
            createOfferUseCase,
            getAllOffersUseCase,
            getOfferByIdUseCase,
            updateOfferUseCase,
            getOffersByNameUseCase
        )

        val createCategoryUseCase: CreateCategoryUseCase by inject()
        val getCategoryUseCase: GetCategoryUseCase by inject()
        val getCategoriesUseCase: GetCategoriesUseCase by inject()
        categoryRoutes(
            createCategoryUseCase,
            getCategoryUseCase,
            getCategoriesUseCase
        )


        val getUserOffersUseCase: GetUserOffersUseCase by inject()
        val getUserInfoUseCase: GetUserInfoUseCase by inject()
        val userWishlistUseCase: UserWishlistUseCase by inject()
        val saveUserUseCase: SaveUserUseCase by inject()
        val updateUserDataUseCase: UpdateUserDataUseCase by inject()
        userRoutes(
            getUserOffersUseCase,
            getUserInfoUseCase,
            userWishlistUseCase,
            saveUserUseCase,
            updateUserDataUseCase
        )


        val ratingService: RatingService by inject()
        ratingRoutes(
            ratingService = ratingService
        )


        get("/hello_world"){
            call.respondSuccess("Hello world", HttpStatusCode.OK)
        }


    }
}

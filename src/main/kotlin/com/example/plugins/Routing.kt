package com.example.plugins

import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.category.domain.usecases.GetCategoriesUseCase
import com.example.features.category.domain.usecases.GetCategoryUseCase
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.features.user.domain.UserService
import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.userRoutes
import com.example.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.nio.file.AccessDeniedException

fun Application.configureRouting() {

    configureStatusPages()

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
    }

}



package com.example.plugins


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
//        val createOfferUseCase: CreateOfferUseCase by inject()
//        val getAllOffersUseCase: GetOffersUseCase by inject()
//        val getOfferByIdUseCase: GetOfferByIdUseCase by inject()
//        val updateOfferUseCase: UpdateOfferUseCase by inject()
//        val getOffersByNameUseCase: GetOffersByNameUseCase by inject()
//        offerRoutes(
//            createOfferUseCase,
//            getAllOffersUseCase,
//            getOfferByIdUseCase,
//            updateOfferUseCase,
//            getOffersByNameUseCase
//        )
//
//        val createCategoryUseCase: CreateCategoryUseCase by inject()
//        val getCategoryUseCase: GetCategoryUseCase by inject()
//        val getCategoriesUseCase: GetCategoriesUseCase by inject()
//        categoryRoutes(
//            createCategoryUseCase,
//            getCategoryUseCase,
//            getCategoriesUseCase
//        )
//
//
//        val getUserOffersUseCase: GetUserOffersUseCase by inject()
//        val getUserInfoUseCase: GetUserInfoUseCase by inject()
//        val userWishlistUseCase: UserWishlistUseCase by inject()
//        val saveUserUseCase: SaveUserUseCase by inject()
//        val updateUserDataUseCase: UpdateUserDataUseCase by inject()
//        userRoutes(
//            getUserOffersUseCase,
//            getUserInfoUseCase,
//            userWishlistUseCase,
//            saveUserUseCase,
//            updateUserDataUseCase
//        )
//
//        get("/hello_world"){
//            call.respondSuccess("Hello world", HttpStatusCode.OK)
//        }
//
//
//        authenticate("auth-jwt") {
//            withRole(Role.ADMIN){
//                get("/admin"){
//                    val principal = call.authentication
//                    if (principal != null) {
//                        call.respond(HttpStatusCode.OK)
//                    } else {
//                        call.respond(HttpStatusCode.Unauthorized)
//                    }
//                }
//
//            }
//            withRole(Role.USER){
//                get("/user"){
//                    call.respondSuccess("Hello user", HttpStatusCode.OK)
//                }
//            }
//
//            get("/oauth") {
//                val principal = call.principal<JwtUserPrincipal>()
//
//                // ...
//
//                call.respondText("${principal?.email}")
//            }
//        }

    }
}

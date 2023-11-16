package com.example.plugins

import com.example.database.DatabaseProvider
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {


        val databaseProvider: DatabaseProvider by inject()
        cleanDbRoute(databaseProvider)

        get("/api/messages/public") {
            call.respondText(
                """{"message": "The API doesn't require an access token to share this message."}""",
                contentType = ContentType.Application.Json
            )
        }

        authenticate("auth0") {
            get("/api/messages/protected") {
                call.respondText(
                    """{"message": "The API successfully validated your access token."}""",
                    contentType = ContentType.Application.Json
                )
            }
        }





//        authenticate("auth-oauth-google") {
//            get("/login") {
//                // Redirects to Google authorization page automatically
//            }
//
//            get("/callback") {
//                val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
//                val token = principal?.accessToken.toString()
//                principal?.tokenType
//                principal?.extraParameters
//                call.respondRedirect("/hello?token=$token")
//            }
//        }
//
//        get("/hello") {
//            val token = call.request.queryParameters["token"]
//            if (token != null) {
//                val userInfo: String = applicationHttpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
//                    headers { append(HttpHeaders.Authorization, "Bearer $token") }
//                }.body()
//                call.respondText("This is your userInfo: $userInfo")
//            } else {
//                call.respond(HttpStatusCode.Unauthorized, "No token provided")
//            }
//        }


        val createOfferUseCase: CreateOfferUseCase by inject()
        val getAllOffersUseCase: GetOffersUseCase by inject()
        val getOfferByIdUseCase: GetOfferByIdUseCase by inject()
        val updateOfferUseCase: UpdateOfferUseCase by inject()
        offerRoutes(createOfferUseCase, getAllOffersUseCase, getOfferByIdUseCase, updateOfferUseCase)

        val categoryRepository: CategoryRepository by inject()
        val createCategoryUseCase: CreateCategoryUseCase by inject()
        categoryRoutes(categoryRepository, createCategoryUseCase)


    }

}

package com.example.plugins

import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.features.user.domain.UserDataRepository
import com.example.features.user.presentation.models.UserRequest
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }

    // Middleware do autoryzacji
    intercept(ApplicationCallPipeline.Features) {
        val token = call.request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ")
        if (token == null) {
            call.respond(HttpStatusCode.Unauthorized, "No token provided")
            finish() // Zakończ przetwarzanie żądania
        } else {
            // Tutaj można dodać logikę weryfikacji tokena
        }
    }

    routing {

        authenticate("auth-oauth-google") {
            get("/login") {
                // Redirects to Google authorization page automatically
            }
            val userRepository: UserDataRepository by inject()
            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
                val token = principal?.accessToken.toString()

                // Pobranie danych użytkownika
                val userInfoJson: String = applicationHttpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers { append(HttpHeaders.Authorization, "Bearer $token") }
                }.body()



                // Konwersja JSON na obiekt User
                val userInfo = Json.decodeFromString<UserRequest>(userInfoJson)

                // Sprawdzenie, czy użytkownik istnieje w bazie danych
                val existingUser = userRepository.getUser(userInfo.id)
                if (existingUser == null) {
                    // Zapisanie nowego użytkownika, jeśli nie istnieje
                    userRepository.saveUser(userInfo)
                } else {
                    // Aktualizacja istniejącego użytkownika
                    userRepository.updateUserInfo(userInfo)
                }

                call.respondRedirect("/hello?token=$token")
            }

        }

        get("/hello") {
            val token = call.request.queryParameters["token"]
            if (token != null) {
                val userInfo: String = applicationHttpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers { append(HttpHeaders.Authorization, "Bearer $token") }
                }.body()
                call.respondText("This is your userInfo: $userInfo")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "No token provided")
            }
        }


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

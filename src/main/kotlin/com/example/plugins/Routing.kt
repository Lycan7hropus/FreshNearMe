package com.example.plugins

import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.features.offer.presentation.categoryRoutes
import com.example.features.offer.presentation.offerRoutes
import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.userRoutes
import com.example.utils.extra.getBearerToken
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    configureStatusPages()

    routing {


        authenticate("auth-bearer") {
            get("/getUserInfo") {
                // Pobieranie tokenu z nagłówka autoryzacji
                val token = call.getBearerToken()

                if (token != null) {
                    val userInfo: String = HttpClient(Apache).get("https://www.googleapis.com/oauth2/v2/userinfo") {
                        headers { append(HttpHeaders.Authorization, "Bearer $token") }
                    }.body()
                    call.respondText("This is your userInfo: $userInfo")
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "No token provided")
                }
            }
        }

        authenticate("auth-bearer") {
            get("/protected-route") {
                val user = call.principal<UserIdPrincipal>() ?: error("Brak uwierzytelnienia")
                call.respondText("Hello, ${user.name}!")
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

        val getUserOffersUseCase: GetUserOffersUseCase by inject()
        val getUserUseCase: GetUserUseCase by inject()
        val getUserWishListUseCase: GetUserWishListUseCase by inject()
        val saveUserUseCase: SaveUserUseCase by inject()
        val updateUserUseCase: UpdateUserUseCase by inject()
        userRoutes(getUserOffersUseCase,getUserUseCase,getUserWishListUseCase,saveUserUseCase,updateUserUseCase)
    }

}



fun Route.withRole(role: String, build: Route.() -> Unit) {
    authenticate("auth-oauth-google") {
        val routeWithRole = createRouteFromPath("")
        routeWithRole.apply(build)
        routeWithRole.intercept(ApplicationCallPipeline.Call) {
            val principal = call.principal<UserIdPrincipal>() ?: error("No principal")
            val userRole = getUserRoleFromDatabase(principal.name) // Pobierz rolę użytkownika z MongoDB

            if (userRole != role) {
                call.respond(HttpStatusCode.Forbidden, "Access denied")
                finish()
            }
        }
    }
}

suspend fun getUserRoleFromDatabase(userId: String): String {
    // Logika do pobrania roli użytkownika z MongoDB
    return "ADMIN" // Przykładowa wartość
}


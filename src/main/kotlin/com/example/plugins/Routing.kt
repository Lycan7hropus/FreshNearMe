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
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
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


    routing {
        authenticate("auth-bearer") {
            get("/getUserInfo") {
                // Pobieranie tokenu z nagłówka autoryzacji
                val token = call.request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ")

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


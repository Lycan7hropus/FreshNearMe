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
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

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

        val categoryRepository: CategoryRepository by inject()
        val createCategoryUseCase: CreateCategoryUseCase by inject()
        categoryRoutes(
            categoryRepository,
            createCategoryUseCase
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


//TODO
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
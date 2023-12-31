package com.example.features.offer.presentation

import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.category.presentation.dto.CategoryDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoryRoutes(categoryRepository: CategoryRepository, createCategoryUseCase: CreateCategoryUseCase) {

    post("/category") {
        val categoryDTO = call.receive<CategoryDTO>()

        val result = createCategoryUseCase.invoke(categoryDTO)

        result.fold(
            onSuccess = { category ->
                call.respond(HttpStatusCode.OK, category)
            },
            onFailure = { exception ->
                call.respond(HttpStatusCode.InternalServerError, exception.message ?: "Error creating offer")
            }
        )
    }

}


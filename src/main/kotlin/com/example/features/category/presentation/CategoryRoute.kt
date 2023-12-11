package com.example.features.offer.presentation

import com.example.features.category.domain.usecases.CreateCategoryUseCase
import com.example.features.category.domain.usecases.GetCategoriesUseCase
import com.example.features.category.domain.usecases.GetCategoryUseCase
import com.example.features.category.presentation.dto.CategoryDto
import com.example.utils.Role
import com.example.utils.extensionFunctions.respondSuccess
import com.example.utils.extensionFunctions.withRole
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import org.koin.ktor.ext.getKoin

fun Route.categoryRoutes(
    createCategoryUseCase: CreateCategoryUseCase = get(),
    getCategoryUseCase: GetCategoryUseCase= get(),
    getCategoriesUseCase: GetCategoriesUseCase = get(),
) {

    route("/categories"){
        withRole(Role.ADMIN){
            post() {
                val categoryDTO = call.receive<CategoryDto>()
                val category = createCategoryUseCase(categoryDTO)
                call.respondSuccess(category)
            }

            put("/{id}") {
                val categoryId = call.parameters["id"] ?: throw MissingRequestParameterException("category id")
                //TODO
            }
        }

        get("") {
            val category = getCategoriesUseCase()
            call.respondSuccess(category)
        }

        get("/{id}") {
            val categoryId = call.parameters["id"] ?: throw MissingRequestParameterException("category id")
            val category = getCategoryUseCase(categoryId)
            call.respondSuccess(category)
        }

    }

}


package presentation


import presentation.dto.CategoryDto
import utils.extensionFunctions.respondSuccess
import utils.extensionFunctions.withRole
import domain.usecases.CreateCategoryUseCase
import domain.usecases.GetCategoriesUseCase
import domain.usecases.GetCategoryUseCase
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import utils.Role

internal fun Route.categoryRoutes(createCategoryUseCase: CreateCategoryUseCase = getKoin().get(), getCategoryUseCase: GetCategoryUseCase = getKoin().get(), getCategoriesUseCase: GetCategoriesUseCase = getKoin().get()) {

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
        get() {
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


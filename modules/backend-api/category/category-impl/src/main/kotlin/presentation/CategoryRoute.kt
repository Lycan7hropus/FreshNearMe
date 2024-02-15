package presentation


import application.CategoryService
import utils.extensionFunctions.respondSuccess
import utils.extensionFunctions.withRole
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import presentation.dto.CategoryDto
import utils.Role

 internal fun Route.categoryRoutes(categoryService: CategoryService = getKoin().get()) {

    route("/categories"){
        withRole(Role.ADMIN){
            post() {
                val categoryDTO = call.receive<CategoryDto>()
                val category = categoryService.createCategory(categoryDTO)
                call.respondSuccess(category)
            }

            put("/{id}") {
                val categoryId = call.parameters["id"] ?: throw MissingRequestParameterException("category id")
                //TODO
            }
        }
        get() {
            val category = categoryService.getCategories()
            call.respondSuccess(category)
        }

        get("/{id}") {
            val categoryId = call.parameters["id"] ?: throw MissingRequestParameterException("category id")
            val category = categoryService.getCategory(categoryId)
            call.respondSuccess(category)
        }

    }

}


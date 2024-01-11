package presentation



import domain.usecases.*
import presentation.dto.OfferDto
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import utils.extensionFunctions.getUserId
import utils.extensionFunctions.respondSuccess
import utils.extensionFunctions.toCoordinates

internal fun Route.offerRoutes(
    createOfferUseCase: CreateOfferUseCase = getKoin().get(),
    getAllOffersUseCase: GetOffersUseCase = getKoin().get(),
    getOfferByIdUseCase: GetOfferByIdUseCase = getKoin().get(),
    updateOfferUseCase: UpdateOfferUseCase = getKoin().get(),
    getOffersByNameUseCase: GetOffersByNameUseCase = getKoin().get()
) {

    // Route for getting a list of all offers
    route("/offers"){
        get() {
            val category = call.request.queryParameters["category"]
            val coordinates = call.request.queryParameters["coordinates"]?.toCoordinates()
            val distance = call.request.queryParameters["distance"]?.toDoubleOrNull()

            val offersDto = getAllOffersUseCase(category, distance, coordinates)
            call.respondSuccess(offersDto)
        }


        get("/search") {
            val name = call.request.queryParameters["name"]
                ?: throw MissingRequestParameterException("offer name")

            val offersDto = getOffersByNameUseCase(name)
            call.respondSuccess(offersDto)
        }


        // Route for getting a single offer by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
            val offerDto = getOfferByIdUseCase(id)
            call.respondSuccess(offerDto)
        }

        // Route for adding an offer
        authenticate("auth-jwt") {
            post() {
                val newOfferDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val createdOffer = createOfferUseCase(newOfferDto, userId)
                call.respondSuccess(createdOffer)
            }

            put<OfferDto>("/{id}") {
                val offerId = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
                val offerToUpdateDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val offer = updateOfferUseCase(offerId, userId, offerToUpdateDto)

                call.respondSuccess(offer)
            }
        }
    }
}


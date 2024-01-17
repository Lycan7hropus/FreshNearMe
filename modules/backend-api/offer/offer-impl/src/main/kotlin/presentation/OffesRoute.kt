package presentation



import application.OfferService
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
    offerService: OfferService = getKoin().get(),
) {
    // Route for getting a list of all offers
    route("/offers"){
        get() {
            val category = call.request.queryParameters["category"]
            val coordinates = call.request.queryParameters["coordinates"]?.toCoordinates()
            val distance = call.request.queryParameters["distance"]?.toDoubleOrNull()

            val offersDto = offerService.getOffers(category, distance, coordinates)
            call.respondSuccess(offersDto)
        }


        get("/search") {
            val name = call.request.queryParameters["name"]
                ?: throw MissingRequestParameterException("offer name")

            val offersDto = offerService.getOffersByName(name)
            call.respondSuccess(offersDto)
        }


        // Route for getting a single offer by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
            val offerDto = offerService.getOfferById(id)
            call.respondSuccess(offerDto)
        }

        // Route for adding an offer
        authenticate("auth-jwt") {
            post() {
                val newOfferDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val createdOffer = offerService.createOffer(newOfferDto, userId)
                call.respondSuccess(createdOffer)
            }

            put<OfferDto>("/{id}") {
                val offerId = call.parameters["id"] ?: throw MissingRequestParameterException("offer id")
                val offerToUpdateDto = call.receive<OfferDto>()
                val userId = call.getUserId()

                val offer = offerService.updateOffer(offerId, userId, offerToUpdateDto)

                call.respondSuccess(offer)
            }
        }
    }
}


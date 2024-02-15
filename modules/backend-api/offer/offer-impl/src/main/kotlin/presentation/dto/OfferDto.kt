package presentation.dto


import domain.Offer
import dto.CategoryApiDto
import utils.models.Coordinates


internal data class OfferDto(
   val id: String?,
   val name: String,
   val categoryId: String,
   val price: Double,
   val phoneNumber: String,
   val description: String,
   val imageUrl: String?,
   val coordinates: Coordinates
) {
   constructor(offer: Offer) : this(
      id = offer.id,
      name = offer.name,
      categoryId = offer.category.id,
      price = offer.price,
      phoneNumber = offer.phoneNumber,
      description = offer.description,
      imageUrl = offer.imageUrl,
      coordinates = Coordinates(longitude = offer.geoPoint.coordinates[0], latitude = offer.geoPoint.coordinates[1])
   )

}


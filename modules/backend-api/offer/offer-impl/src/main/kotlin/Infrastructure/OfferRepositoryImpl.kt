package Infrastructure


import dto.CategoryApiDto
import utils.DatabaseOperationException
import utils.OfferCreationException
import utils.models.Coordinates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.geojson.Point
import com.mongodb.client.model.geojson.Position
import domain.Offer
import domain.OfferRepository
import io.ktor.server.plugins.*
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection

internal class OfferRepositoryImpl(private val offersCollection: CoroutineCollection<Offer>) : OfferRepository {
    override suspend fun getOffers(category: CategoryApiDto?, distance: Double?, coordinates: Coordinates?): List<Offer> {
        val filters = mutableListOf<Bson>()
        val point = coordinates?.let { Point(Position(it.longitude, it.latitude)) }

        // Add category filter if category is not null
        category?.path?.let {
            filters.add(Filters.regex(Offer::category.name + "." + CategoryApiDto::path.name, "^${Regex.escape(it)}"))
        }

        // Add location filter if geoPoint and distance are not null
        if (point != null && distance != null) {
            filters.add(
                Filters.near(Offer::geoPoint.name, point, distance * 1000, 0.0) // distance in meters
            )
        }

        // Combine filters with 'and' if there are multiple filters
        val query = if (filters.size > 1) Filters.and(filters) else filters.firstOrNull()

        return if (query != null) {
            offersCollection.find(query).toList()
        } else {
            offersCollection.find().toList() // Return all offers if no filters are applied
        }
    }

    override suspend fun getOfferById(offerId: String): Offer {
        return offersCollection.findOneById(offerId) ?: throw NotFoundException("Offer with id $offerId not found")
    }


    override suspend fun saveOffer(offer: Offer): Offer {
        return try {
            offersCollection.insertOne(offer)
            offer
        } catch (e: Exception) {
            throw OfferCreationException("Nie udało się utworzyć oferty: ${e.message}")
        }
    }


    override suspend fun updateOffer(offer: Offer): Offer {
        val updateResult = offersCollection.updateOneById(offer.id, offer)
        return if (updateResult.wasAcknowledged() && updateResult.matchedCount > 0) {
            offer // Return the updated offer
        } else {
            throw DatabaseOperationException("Database operation failed for offer with id ${offer.id}")

        }
    }

    override suspend fun deleteOffer(offerId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun findOffersWithQuery(query: String): List<Offer> {
        // Use a regular expression filter to perform a case-insensitive search
        val regex = Filters.regex(Offer::name.name, ".*$query.*", "i")

        return try {
            offersCollection.find(regex).toList()
        } catch (e: Exception) {
            throw DatabaseOperationException("Error searching for offer with name: ${e.message}")
        }
    }
}
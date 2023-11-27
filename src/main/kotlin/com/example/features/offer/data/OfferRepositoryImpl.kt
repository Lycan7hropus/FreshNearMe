package com.example.features.offer.data

import com.example.features.category.domain.Category
import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository
import com.example.models.Coordinates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.geojson.Point
import com.mongodb.client.model.geojson.Position
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection

class OfferRepositoryImpl(private val offersCollection: CoroutineCollection<Offer>) : OfferRepository {
    override suspend fun getOffers(category: Category?, distance: Double?, coordinates: Coordinates?): List<Offer> {
        val filters = mutableListOf<Bson>()
        val point = coordinates?.let { Point(Position(it.longitude, it.latitude)) }

        // Add category filter if category is not null
        category?.path?.let {
            filters.add(Filters.regex(Offer::category.name + "." + Category::path.name, "^${Regex.escape(it)}"))
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

    override suspend fun getOfferById(offerId: String): Offer? {
        return try {
            offersCollection.findOneById(offerId)
        } catch (e: Exception) {
            // Handle any database exceptions
            null
        }
    }

    override suspend fun saveOffer(offer: Offer): Offer? {
        // Insert the new offer into the database collection
        return try {
            offersCollection.insertOne(offer)
            offer // Return the offer with the ID assigned by the database
        } catch (e: Exception) {
            // Handle any database exceptions and return null if the operation fails
            null
        }
    }

    override suspend fun updateOffer(offer: Offer): Offer? {
        val updateResult = offersCollection.updateOneById(offer.id, offer)
        return if (updateResult.wasAcknowledged() && updateResult.matchedCount > 0) {
            offer // Return the updated offer
        } else {
            null // Return null if the update didn't match any document
        }
    }

    override suspend fun deleteOffer(offerId: String): Boolean {
        TODO("Not yet implemented")
    }
}
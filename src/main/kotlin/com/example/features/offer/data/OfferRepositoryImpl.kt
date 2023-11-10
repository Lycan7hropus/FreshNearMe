package com.example.features.offer.data

import com.example.models.Offer
import org.litote.kmongo.coroutine.CoroutineCollection

class OfferRepositoryImpl(private val offersCollection: CoroutineCollection<Offer>) : OfferRepository {
    override suspend fun getOffers(): List<Offer> {
        return try {
            offersCollection.find().toList()
        } catch (e: Exception) {
            // Handle any database exceptions
            emptyList()
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

    override suspend fun createOffer(offer: Offer): Offer? {
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
}
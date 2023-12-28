package com.example.di

import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import com.example.features.category.domain.Category
import com.example.features.offer.domain.Offer
import com.example.features.rating.domain.models.Rating
import com.mongodb.client.model.Indexes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val databaseModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }

    single<CoroutineCollection<Category>>(named("CategoryCollection")) {
        get<DatabaseProvider>().database.getCollection("categories")
    }
    single<CoroutineCollection<Category>>(named("UserCollection")) {
        get<DatabaseProvider>().database.getCollection("users")
    }

    single<CoroutineCollection<Rating>>(named("RatingCollection")) {
        val ratingCollection: CoroutineCollection<Rating> = get<DatabaseProvider>().database.getCollection("ratings")
        CoroutineScope(Dispatchers.IO).launch {
            ratingCollection.createIndex(Indexes.ascending("rated_user_id"))
            ratingCollection.createIndex(Indexes.ascending("rating_user_id"))
        }
        ratingCollection
    }

    single<CoroutineCollection<Offer>>(named("OfferCollection")) {
        val offerCollection: CoroutineCollection<Offer> = get<DatabaseProvider>().database.getCollection("offers")
        CoroutineScope(Dispatchers.IO).launch {
            offerCollection.createIndex(Indexes.geo2dsphere(Offer::geoPoint.name))
        }
        offerCollection
    }
}

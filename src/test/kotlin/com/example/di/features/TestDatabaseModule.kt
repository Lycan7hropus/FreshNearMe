package com.example.di.features

import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import com.example.database.TestMongoDatabaseProvider
import com.example.features.category.domain.Category
import com.example.features.offer.domain.Offer
import com.mongodb.client.model.Indexes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

val testDatabaseModule = module {

    single<DatabaseProvider> { TestMongoDatabaseProvider("test_db") }

    single<CoroutineCollection<Category>>(named("TestCategoryCollection")) {
        get<DatabaseProvider>().database.getCollection("categories")
    }
    single<CoroutineCollection<Category>>(named("TestUserCollection")) {
        get<DatabaseProvider>().database.getCollection("users")
    }
    single<CoroutineCollection<Offer>>(named("TestOfferCollection")) {
        val offerCollection: CoroutineCollection<Offer> = get<DatabaseProvider>().database.getCollection("offers")
        CoroutineScope(Dispatchers.IO).launch {
            offerCollection.createIndex(Indexes.geo2dsphere(Offer::geoPoint.name))
        }
        offerCollection
    }
}

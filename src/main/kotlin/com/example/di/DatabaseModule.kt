package com.example.di
import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.models.Category
import com.example.models.Offer
import com.mongodb.client.model.Indexes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val databaseModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }
    single<CoroutineCollection<Offer>> {
        val offerCollection: CoroutineCollection<Offer> = get<DatabaseProvider>().database.getCollection("offers")
        GlobalScope.launch {
            offerCollection.createIndex(Indexes.geo2dsphere(Offer::geoPoint.name))
        }
        offerCollection
    }
    single<CoroutineCollection<Category>> {
        get<DatabaseProvider>().database.getCollection("categories")
    }
}

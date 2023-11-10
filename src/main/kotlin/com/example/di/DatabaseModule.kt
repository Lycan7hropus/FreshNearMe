package com.example.di
import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import com.example.features.offer.data.OfferRepository
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.models.Offer
import com.mongodb.reactivestreams.client.MongoCollection
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val databaseModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }
    single<OfferRepository> { OfferRepositoryImpl(get()) }
    single<CoroutineCollection<Offer>> {
        get<DatabaseProvider>().database.getCollection("offers")
    }

}
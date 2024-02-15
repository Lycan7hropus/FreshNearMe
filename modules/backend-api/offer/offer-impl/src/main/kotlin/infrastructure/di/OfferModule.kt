package infrastructure.di


import com.example.OfferApi
import database.DatabaseProvider
import com.mongodb.client.model.Indexes
import infrastructure.OfferRepositoryImpl
import domain.Offer
import application.OfferApiImpl
import application.OfferService
import application.OfferServiceImpl
import domain.OfferRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val offerModule = module {
    single<CoroutineCollection<Offer>>(named("OfferCollection")) {
        val offerCollection: CoroutineCollection<Offer> = get<DatabaseProvider>().database.getCollection("offers")
        CoroutineScope(Dispatchers.IO).launch {
            offerCollection.createIndex(Indexes.geo2dsphere(Offer::geoPoint.name))
        }
        offerCollection
    }

    single<OfferRepository> { OfferRepositoryImpl(get(named("OfferCollection"))) }

    single<OfferService> { OfferServiceImpl(get(), get(), get()) }

    single<OfferApi> { OfferApiImpl() }
}

package di


import com.example.OfferApi
import com.example.database.DatabaseProvider
import com.mongodb.client.model.Indexes
import data.OfferRepositoryImpl
import domain.Offer
import domain.OfferApiImpl
import domain.OfferRepository
import domain.usecases.*
import domain.usecases.CreateOfferUseCase
import domain.usecases.GetOfferByIdUseCase
import domain.usecases.GetOffersByNameUseCase
import domain.usecases.GetOffersUseCase
import domain.usecases.UpdateOfferUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

internal val offerModule = module {
    single<CoroutineCollection<Offer>>(named("OfferCollection")) {
        val offerCollection: CoroutineCollection<Offer> = get<DatabaseProvider>().database.getCollection("offers")
        CoroutineScope(Dispatchers.IO).launch {
            offerCollection.createIndex(Indexes.geo2dsphere(Offer::geoPoint.name))
        }
        offerCollection
    }

    single<OfferRepository> { OfferRepositoryImpl(get(named("OfferCollection"))) }

    single { CreateOfferUseCase(get(), get()) }
    single { GetOffersUseCase(get(), get()) }
    single { GetOfferByIdUseCase(get()) }
    single { UpdateOfferUseCase(get(), get()) }
    single { GetOffersByNameUseCase(get()) }

    single<OfferApi> { OfferApiImpl() }
}

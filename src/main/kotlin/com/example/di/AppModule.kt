package com.example.di
import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import com.example.features.category.data.CategoryRepositoryImpl
import com.example.features.category.domain.CategoryRepository
import com.example.features.category.domain.usecases.*
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.data.OfferRepositoryImpl
import com.example.features.offer.domain.CategoryProvider
import com.example.features.offer.domain.usecases.CreateOfferUseCase
import com.example.features.offer.domain.usecases.GetOfferByIdUseCase
import com.example.features.offer.domain.usecases.GetOffersUseCase
import com.example.features.offer.domain.usecases.UpdateOfferUseCase
import com.example.models.Category
import com.example.models.Offer
import com.example.utils.CategoryACL
import com.mongodb.client.model.Indexes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection
import org.slf4j.LoggerFactory

val appModule = module {
    single { LoggerFactory.getLogger("logger") }
}

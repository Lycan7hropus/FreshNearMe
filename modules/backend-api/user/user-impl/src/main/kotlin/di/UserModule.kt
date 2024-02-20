package di


import UserApi
import app.UserApiImpl
import database.DatabaseProvider
import domain.usecases.GetUserInfoUseCase
import domain.usecases.GetUserOffersUseCase
import domain.usecases.SaveUserUseCase
import domain.usecases.UpdateUserDataUseCase
import domain.usecases.UserWishlistUseCase
import infra.UserDataRepositoryImpl
import domain.UserDataRepository
import domain.models.User
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val userModule = module {
    single<CoroutineCollection<User>>(named("UserCollection")) {
        get<DatabaseProvider>().database.getCollection("users")
    }

    single<UserDataRepository> { UserDataRepositoryImpl(get(named("UserCollection"))) }
    single<UserApi> { UserApiImpl(get()) }

    single { GetUserOffersUseCase(get()) }
    single { GetUserInfoUseCase(get()) }
    single { UserWishlistUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { UpdateUserDataUseCase(get()) }
}

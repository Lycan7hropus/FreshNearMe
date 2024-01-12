package di


import database.DatabaseProvider
import com.example.features.user.domain.usecases.GetUserInfoUseCase
import com.example.features.user.domain.usecases.GetUserOffersUseCase
import com.example.features.user.domain.usecases.SaveUserUseCase
import com.example.features.user.domain.usecases.UpdateUserDataUseCase
import com.example.features.user.domain.usecases.UserWishlistUseCase
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

    single { GetUserOffersUseCase(get()) }
    single { GetUserInfoUseCase(get()) }
    single { UserWishlistUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { UpdateUserDataUseCase(get()) }
}

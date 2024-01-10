package di


import com.example.database.DatabaseProvider
import domain.Category
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val categoryDatabaseModule = module {
    single<CoroutineCollection<Category>>(named("CategoryCollection")) {
        get<DatabaseProvider>().database.getCollection("categories")
    }
    single<CoroutineCollection<Category>>(named("UserCollection")) {
        get<DatabaseProvider>().database.getCollection("users")
    }
}

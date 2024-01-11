package di

import database.DatabaseProvider
import database.MongoDatabaseProvider
import org.koin.dsl.module

val databaseModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }
}

package di

import database.DatabaseProvider
import database.MongoDatabaseProvider
import io.ktor.client.*
import org.koin.dsl.module
import org.slf4j.LoggerFactory

val commonModule = module {
    single { LoggerFactory.getLogger("logger") }
    single { HttpClient() }

    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }
}

package com.example.di

import com.example.database.DatabaseProvider
import com.example.database.MongoDatabaseProvider
import org.koin.dsl.module

val databaseModule = module {
    single<DatabaseProvider> { MongoDatabaseProvider("your_db_name") }
}

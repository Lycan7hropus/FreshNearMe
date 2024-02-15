package infra.di


import CategoryApi
import application.CategoryApiImpl
import application.CategoryService
import application.CategoryServiceImpl
import database.DatabaseProvider
import infra.CategoryRepositoryImpl
import domain.enitties.Category
import domain.CategoryRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val categoryModule = module {
    single<CoroutineCollection<Category>>(named("CategoryCollection")) {
        get<DatabaseProvider>().database.getCollection("categories")
    }

    single<CategoryRepository> { CategoryRepositoryImpl(get(named("CategoryCollection"))) }

    single <CategoryService>{ CategoryServiceImpl(get()) }
    single <CategoryApi>{ CategoryApiImpl(get()) }

}

package di


import database.DatabaseProvider
import data.CategoryRepositoryImpl
import domain.enitties.Category
import domain.CategoryRepository
import domain.usecases.CreateCategoryUseCase
import domain.usecases.DeleteCategoryUseCase
import domain.usecases.GetCategoriesUseCase
import domain.usecases.GetCategoryUseCase
import domain.usecases.UpdateCategoryUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection

val categoryModule = module {
    single<CoroutineCollection<Category>>(named("CategoryCollection")) {
        get<DatabaseProvider>().database.getCollection("categories")
    }

    single<CategoryRepository> { CategoryRepositoryImpl(get(named("CategoryCollection"))) }

    single { CreateCategoryUseCase(get()) }
    single { DeleteCategoryUseCase(get()) }
    single { GetCategoriesUseCase(get()) }
    single { GetCategoryUseCase(get()) }
    single { UpdateCategoryUseCase() }
}

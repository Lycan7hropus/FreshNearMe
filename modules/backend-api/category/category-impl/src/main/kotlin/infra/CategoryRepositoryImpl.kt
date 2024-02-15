package infra

import utils.DatabaseOperationException
import domain.enitties.Category
import domain.CategoryRepository
import io.ktor.server.plugins.*
import org.litote.kmongo.coroutine.CoroutineCollection

internal class CategoryRepositoryImpl(private val categoryCollection: CoroutineCollection<Category>) : CategoryRepository {
    override suspend fun saveCategory(category: Category): Category {
        val result = categoryCollection.insertOne(category)
        if (result.wasAcknowledged()) {
            return category
        } else {
            throw DatabaseOperationException("Failed to insert category into database")
        }
    }

    override suspend fun getCategoryById(id: String): Category {
        return categoryCollection.findOneById(id) ?: throw NotFoundException("Category not found")
    }

    override suspend fun getCategoryPathById(id: String): String? {
        return categoryCollection.findOneById(id)?.path
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryCollection.find().toList()
    }

    override suspend fun updateCategory(category: Category): Category {
        try {
            val updateResult = categoryCollection.updateOneById(category.id, category)

            if (updateResult.wasAcknowledged() && updateResult.matchedCount > 0) {
                return category
            } else {
                val reason = if (updateResult.matchedCount.toInt() == 0) "Category not found with id: ${category.id}" else "Unknown reason"
                throw DatabaseOperationException("Updating category failed: $reason")
            }
        } catch (e: Exception) {
            throw DatabaseOperationException("Updating category failed due to an exception: ${e.message}")
        }
    }


    override suspend fun deleteCategory(id: String): Boolean {
        //TODO
        return false
    }
}

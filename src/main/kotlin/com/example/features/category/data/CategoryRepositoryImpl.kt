package com.example.features.category.data

import com.example.features.category.domain.Category
import com.example.features.category.domain.CategoryRepository
import org.litote.kmongo.coroutine.CoroutineCollection

class CategoryRepositoryImpl(private val categoryCollection: CoroutineCollection<Category>) : CategoryRepository {
    override suspend fun saveCategory(category: Category): Category {
        categoryCollection.insertOne(category)
        return category
    }

    override suspend fun getCategoryById(id: String): Category? {
        return categoryCollection.findOneById(id)
    }

    override suspend fun getCategoryPathById(id: String): String? {
        return categoryCollection.findOneById(id)?.path
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryCollection.find().toList()
    }

    override suspend fun updateCategory(category: Category): Category? {
        val updateResult = categoryCollection.updateOneById(category.id, category)
        return if (updateResult.wasAcknowledged() && updateResult.matchedCount > 0) {
            category
        } else {
            null
        }
    }

    override suspend fun deleteCategory(id: String): Boolean {
//        val deleteResult = categoryCollection.deleteOneById(id)
//        return deleteResult.wasAcknowledged() && deleteResult.deletedCount > 0
        //TODO
        return false
    }
}

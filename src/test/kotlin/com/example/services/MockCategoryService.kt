package com.example.services

import com.example.features.category.domain.Category
import com.example.features.offer.domain.CategoryService

class MockCategoryService() : CategoryService{

    private val predefinedCategories = mapOf(
        "food" to Category(id = "1", name = "Food", parentId = null, path = "/Food"),
        "beer" to Category(id = "2", name = "Beer", parentId = "1", path = "/Food/Beer"),
    )

    override suspend fun getCategoryById(id: String): Category {
        TODO("Not yet implemented")
    }


    fun getCategory(name: String): Category {
        return predefinedCategories[name]!!
    }

}
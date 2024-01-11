package domain

internal interface CategoryRepository {
    suspend fun saveCategory(category: Category): Category
    suspend fun getCategoryById(id: String): Category
    suspend fun getCategoryPathById(id: String): String?
    suspend fun getAllCategories(): List<Category>
    suspend fun updateCategory(category: Category): Category?
    suspend fun deleteCategory(id: String): Boolean
}
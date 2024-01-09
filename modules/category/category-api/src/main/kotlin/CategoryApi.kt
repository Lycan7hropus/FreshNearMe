interface CategoryApi {
    suspend fun getCategoryById(id: String): CategoryApiDto
}
package infra

import dto.CategoryApiDto
import dto.CategoryDto
import utils.transform
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MappingKtTest {
    @Test
    fun testToDto() {
        val result: CategoryDto = Category("id", "name", "parentId", "path").transform()
        assertEquals(CategoryDto("parentId", "name"), result)
    }

    @Test
    fun testToApiDto() {
        val result: CategoryApiDto = Category("id", "name", "parentId", "path").transform()
        assertEquals(CategoryApiDto("id", "name", "parentId", "path"), result)
    }

    data class Category(val id: String, val name: String, val parentId: String?, val path: String)
}
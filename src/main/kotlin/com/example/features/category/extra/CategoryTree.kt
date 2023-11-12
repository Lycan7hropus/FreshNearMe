package com.example.features.category.extra

import com.example.models.Category

fun buildCategoryTree(categories: List<Category>, parentId: String? = null): String {
    val childCategories = categories.filter { it.parentId == parentId }
    if (childCategories.isEmpty()) return ""

    return buildString {
        append("<ul>")
        for (category in childCategories) {
            append("<li>")
            append("${category.name} (${category.id})")
            append(buildCategoryTree(categories, category.id)) // Rekurencyjne tworzenie poddrzew
            append("</li>")
        }
        append("</ul>")
    }
}

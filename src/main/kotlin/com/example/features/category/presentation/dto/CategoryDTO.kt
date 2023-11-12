package com.example.features.category.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val parentId: String?,
    val name: String
)

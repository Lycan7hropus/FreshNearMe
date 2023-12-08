package com.example.features.category.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
public data class CategoryDto(
    val parentId: String?,
    val name: String
)

package com.example.features.offer.domain

import com.example.models.Category

interface CategoryProvider {
    suspend fun getCategoryById(id: String): Category?
}
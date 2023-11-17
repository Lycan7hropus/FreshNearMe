package com.example.features.user.presentation.models

data class UserRequest (
    val id: String,
    val name: String,
    val given_name: String,
    val family_name: String,
    val picture: String,
    val locale: String
)

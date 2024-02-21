package com.example.chat.presentation.models

import kotlinx.serialization.Serializable


@Serializable
data class MessageDto(
    val roomId:String,
    val content:String,
)
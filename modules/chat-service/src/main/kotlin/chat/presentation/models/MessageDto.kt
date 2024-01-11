package com.example.chat.presentation.models

data class MessageDto(
    val senderId:String,
    val roomId:String,
    val content:String,
)
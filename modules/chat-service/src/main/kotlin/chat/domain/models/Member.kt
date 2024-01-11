package com.example.chat.domain.models

import io.ktor.websocket.*

data class Member(
    val userId: String,
    val sessionId: String,
    val socket: WebSocketSession
)

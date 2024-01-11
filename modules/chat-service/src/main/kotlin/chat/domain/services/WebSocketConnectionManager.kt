package com.example.chat.domain.services

import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class WebSocketConnectionManager {
    private val userConnections = ConcurrentHashMap<String, WebSocketSession>()

    fun addSession(userId: String, session: WebSocketSession) {
        userConnections[userId] = session
    }

    fun removeSession(userId: String) {
        userConnections.remove(userId)
    }

    fun getSession(userId: String): WebSocketSession? {
        return userConnections[userId]
    }
}

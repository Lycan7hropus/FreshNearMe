package com.example

import com.example.plugins.configureRouting
import com.example.plugins.*
import com.example.utils.ApiResponse
import com.google.gson.Gson
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*
class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/hello_world").apply {
            var gson = Gson()
            assertEquals(HttpStatusCode.OK, status)
            val gsonExpected = gson.toJson(ApiResponse.Success("Hello world"))
            assertEquals(gsonExpected, bodyAsText())
        }
    }
}



package com.example

import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import org.koin.test.KoinTest

abstract class BaseRoutingTest : KoinTest {
    abstract fun implementModules(): Application.() -> Unit
    protected fun baseRouteTestApplication(block: suspend ApplicationTestBuilder.() -> Unit) {
        testApplication {
            environment {
                config = MapApplicationConfig("ktor.environment" to "dev")
            }
            application(implementModules())
            block()
        }
    }
}

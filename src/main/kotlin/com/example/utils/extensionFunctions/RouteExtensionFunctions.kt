package com.example.utils.extensionFunctions

import com.example.utils.ResourceAccessDenied
import com.example.utils.Role
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.withRole(role: Role, build: Route.() -> Unit) {
    apply(build)
    intercept(ApplicationCallPipeline.Call) {
        val userRole = call.getUserRole()

        if (userRole != role) {
            call.respondError(ResourceAccessDenied("User with role ${role.toString()} cannot access this resource"))
            finish()
        }
    }
}

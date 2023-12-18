package com.example.utils.extensionFunctions

import com.example.utils.ResourceAccessDenied
import com.example.utils.Role
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.withRole(role: Role, build: Route.() -> Unit) =
    withRolesCheck(build) { userRoles -> userRoles.contains(role) }

fun Route.withAllRoles(vararg roles: Role, build: Route.() -> Unit) =
    withRolesCheck(build) { userRoles -> roles.all { role -> userRoles.contains(role) } }

fun Route.withAnyRole(vararg roles: Role, build: Route.() -> Unit) =
    withRolesCheck(build) { userRoles -> roles.any { role -> userRoles.contains(role) } }

fun Route.withoutRoles(vararg roles: Role, build: Route.() -> Unit) =
    withRolesCheck(build) { userRoles -> roles.none { role -> userRoles.contains(role) } }

private fun Route.withRolesCheck(build: Route.() -> Unit, check: (List<Role>) -> Boolean) {
    val roleSpecificRoute = createChild(object : RouteSelector() {
        override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
            RouteSelectorEvaluation.Constant
    })

    roleSpecificRoute.build()

    roleSpecificRoute.intercept(ApplicationCallPipeline.Call) {
        val userRoles = call.getUserRoles()
        if (!check(userRoles)) {
            call.respondError(ResourceAccessDenied("Access denied. User does not have the correct role(s)."))
            finish()
        }
    }
}


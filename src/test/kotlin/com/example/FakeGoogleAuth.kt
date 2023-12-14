package com.example

//import com.example.features.authentication.domain.GoogleAuthService
//import com.example.features.user.domain.UserService
//import com.example.models.UserPrincipal
//import com.example.utils.Role
//import io.ktor.server.application.*
//import io.ktor.server.auth.*
//import io.mockk.coEvery
//import io.mockk.mockk
//import org.koin.ktor.ext.getKoin
//
//private fun Application.configureTestSecurity(googleAuthenticationService: GoogleAuthService = getKoin().get(), userService: UserService = getKoin().get()) {
//    install(Authentication) {
//        bearer("auth-bearer") {
//            realm = "Test realm"
//            authenticate { tokenCredential ->
//                coEvery { mockGoogleAuthService.verifyGoogleToken(any())} returns "mockedGoogleId"
//                coEvery { userService.findUserByGoogleId(tokenCredential.token) } returns mockk()
//                coEvery { userService.getRole(tokenCredential.token) } returns Role.USER
//
//
//                val googleId = googleAuthenticationService.verifyGoogleToken(tokenCredential.token)
//                val userId = userService.findUserByGoogleId(googleId).id
//                val userRole = userService.getRole(userId)
//                UserPrincipal(userId, userRole)
//            }
//        }
//    }
//}
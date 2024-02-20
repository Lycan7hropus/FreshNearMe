package presentation

import AuthEventDto
import UserApi
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin
import org.litote.kmongo.json
import utils.extensionFunctions.respondSuccess
import utils.extensionFunctions.validateHmacSignature
import javax.crypto.spec.SecretKeySpec
import javax.naming.ConfigurationException
import kotlin.text.Charsets.UTF_8

private const val HMAC_ALGORITHM = "HmacSHA256"
fun Route.authRoutes(userApi: UserApi = getKoin().get(), secretKey: String? = System.getenv("SECRET_KEY")) {


    post("/register") {
        val hmacKey = secretKey?.toByteArray(UTF_8)?.let { SecretKeySpec(it, HMAC_ALGORITHM) }
            ?: throw ConfigurationException("Secret key is not configured properly.")


        val authEventDto = call.receive<AuthEventDto>() // Odczytaj ciało żądania jako tekst
        call.validateHmacSignature(authEventDto.json,hmacKey)

        val offersResponse = userApi.saveUser(authEventDto)
        call.respondSuccess(data = offersResponse)
    }



    post("/login") {
        val hmacKey = secretKey?.toByteArray(UTF_8)?.let { SecretKeySpec(it, HMAC_ALGORITHM) }
            ?: throw ConfigurationException("Secret key is not configured properly.")



        val authEventDto = call.receive<AuthEventDto>() // Odczytaj ciało żądania jako tekst
        call.validateHmacSignature(authEventDto.json,hmacKey)

        val offersResponse = userApi.updateUser(authEventDto)
        call.respondSuccess(data = offersResponse)
    }

}

package utils.extensionFunctions

import utils.models.JwtUserPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import utils.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


fun ApplicationCall.getUserId(): String {
    return this.principal<JwtUserPrincipal>()?.sub
        ?: throw InvalidPrincipalException("User ID extraction failed: JWT token may be invalid or missing.")
}
fun ApplicationCall.validateHmacSignature(body: String, hmacKey: SecretKeySpec) {
    val receivedHmacSignature = request.headers["X-HMAC-Signature"]
        ?: throw BadRequestException("Missing HMAC signature")

    val mac = Mac.getInstance("HmacSHA256").apply { init(hmacKey) }
    val calculatedHmac = mac.doFinal(body.toByteArray(Charsets.UTF_8))
    val generatedHmacHex = calculatedHmac.joinToString("") { "%02x".format(it) }

    if (!generatedHmacHex.equals(receivedHmacSignature, ignoreCase = true)) {
        throw BadRequestException("Invalid HMAC signature")
    }
}



fun ApplicationCall.getUserRoles(): List<Role> {
    return this.principal<JwtUserPrincipal>()?.roles
        ?: throw InvalidPrincipalException("User roles extraction failed: JWT token may be invalid or missing.")
}
suspend fun <T> ApplicationCall.respondSuccess(data: T, code: HttpStatusCode = HttpStatusCode.OK) {
    val response = ApiResponse.Success(data)
    this.respond(code, response)
}

suspend fun ApplicationCall.respondError(exception: Exception, code: HttpStatusCode? = null) {
    val (status, apiError) = handleException(exception)
    val response = ApiResponse.Error(apiError)
    this.respond(code ?: status, response)
}

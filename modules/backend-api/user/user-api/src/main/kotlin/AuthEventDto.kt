data class AuthEventDto(
    val id: String,
    val time: Long,
    val type: String,
    val realmId: String,
    val clientId: String,
    val userId: String,
    val sessionId: String?,
    val ipAddress: String,
    val error: String?,
    val details: Details
) {
    data class Details(
        val auth_method: String,
        val auth_type: String,
        val register_method: String,
        val last_name: String,
        val redirect_uri: String,
        val first_name: String,
        val code_id: String,
        val email: String,
        val username: String
    )
}
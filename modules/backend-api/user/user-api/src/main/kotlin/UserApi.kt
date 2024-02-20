interface UserApi {
    fun saveUser(user: AuthEventDto):AuthEventDto

    fun updateUser(user: AuthEventDto):AuthEventDto
}
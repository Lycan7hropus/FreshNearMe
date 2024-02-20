package app

import AuthEventDto
import UserApi
import domain.UserDataRepository

internal class UserApiImpl(userDataRepository: UserDataRepository): UserApi {
    override fun saveUser(user: AuthEventDto): AuthEventDto {
        println(user)
        return user
    }

    override fun updateUser(user: AuthEventDto): AuthEventDto {
        println(user)
        return user
    }
}
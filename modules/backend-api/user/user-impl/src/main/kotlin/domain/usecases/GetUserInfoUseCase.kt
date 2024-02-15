package domain.usecases

import domain.UserDataRepository
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto

internal class GetUserInfoUseCase(private val userRepository: UserDataRepository) {
    suspend fun getBasicInfo(id: String): BasicUserDto {
        return userRepository.getUser(id).toBasicDTO()
    }

    suspend fun getDetailedInfo(id: String): DetailedUserDto {
        return userRepository.getUser(id).toDetailedDTO()
    }
}


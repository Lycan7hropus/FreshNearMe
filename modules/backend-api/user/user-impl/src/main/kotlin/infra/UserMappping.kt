package infra

import domain.models.User
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import infra.mappers.BasicUserDtoConverter
import infra.mappers.DetailedUserDtoConverter
import utils.toDto

internal fun User.toBasicDto(): BasicUserDto {
    val dto = this.toDto(BasicUserDtoConverter::class)
    return dto
}

internal fun User.toDetailedDto(): DetailedUserDto {
    val apiDto = this.toDto(DetailedUserDtoConverter::class)
    return apiDto
}

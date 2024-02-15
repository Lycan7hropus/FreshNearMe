package infra

import domain.models.User
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import infra.mappers.BasicUserDtoConverter
import infra.mappers.DetailedUserDtoConverter

internal fun User.toBasicDto(): BasicUserDto {
    return BasicUserDtoConverter.INSTANCE.convertToDto(this)
}

internal fun User.toDetailedDto(): DetailedUserDto {
    return DetailedUserDtoConverter.INSTANCE.convertToDto(this)
}

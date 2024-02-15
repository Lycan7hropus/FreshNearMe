package infra.mappers

import domain.models.User
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import org.mapstruct.Mapper
import utils.DtoDomainConverter

@Mapper
internal interface BasicUserDtoConverter: DtoDomainConverter<User, BasicUserDto> {
    override fun convertToDto(obj: User): BasicUserDto
    override fun convertToModel(objDto: BasicUserDto): User
}

@Mapper
internal interface DetailedUserDtoConverter: DtoDomainConverter<User, DetailedUserDto> {
    override fun convertToDto(obj: User): DetailedUserDto
    override fun convertToModel(objDto: DetailedUserDto): User
}


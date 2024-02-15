package infra.mappers

import domain.models.User
import presentation.models.BasicUserDto
import presentation.models.DetailedUserDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import utils.DtoDomainConverter

@Mapper
internal interface BasicUserDtoConverter: DtoDomainConverter<User, BasicUserDto> {
    companion object {
        val INSTANCE: BasicUserDtoConverter = Mappers.getMapper(BasicUserDtoConverter::class.java)
    }

    override fun convertToDto(obj: User): BasicUserDto
    override fun convertToModel(objDto: BasicUserDto): User
}

@Mapper
internal interface DetailedUserDtoConverter: DtoDomainConverter<User, DetailedUserDto> {

    companion object {
        val INSTANCE: DetailedUserDtoConverter = Mappers.getMapper(DetailedUserDtoConverter::class.java)
    }


    override fun convertToDto(obj: User): DetailedUserDto
    override fun convertToModel(objDto: DetailedUserDto): User
}


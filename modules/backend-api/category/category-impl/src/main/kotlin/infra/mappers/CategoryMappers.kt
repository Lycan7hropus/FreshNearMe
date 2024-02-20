package infra.mappers


import domain.enitties.Category
import dto.CategoryApiDto
import presentation.dto.CategoryDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import utils.DtoDomainConverter

@Mapper
internal interface CategoryDtoConverter: DtoDomainConverter<Category, CategoryDto> {
    companion object {
        val INSTANCE: CategoryDtoConverter = Mappers.getMapper(CategoryDtoConverter::class.java)
    }


    override fun convertToDto(obj: Category): CategoryDto
    override fun convertToModel(objDto: CategoryDto): Category
}

@Mapper
internal interface CategoryApiDtoConverter : DtoDomainConverter<Category, CategoryApiDto> {

    companion object {
        val INSTANCE: CategoryApiDtoConverter = Mappers.getMapper(CategoryApiDtoConverter::class.java)
    }

    override fun convertToDto(obj: Category): CategoryApiDto
    override fun convertToModel(objDto: CategoryApiDto): Category
}

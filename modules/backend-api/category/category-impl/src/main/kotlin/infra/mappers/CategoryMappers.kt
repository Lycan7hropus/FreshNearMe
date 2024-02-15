package infra.mappers


import domain.enitties.Category
import dto.CategoryApiDto
import dto.CategoryDto
import org.mapstruct.Mapper
import utils.DtoDomainConverter

@Mapper
internal interface CategoryDtoConverter: DtoDomainConverter<Category, CategoryDto> {
    override fun convertToDto(obj: Category): CategoryDto
    override fun convertToModel(objDto: CategoryDto): Category
}

@Mapper
internal interface CategoryApiDtoConverter : DtoDomainConverter<Category, CategoryApiDto> {
    override fun convertToDto(obj: Category): CategoryApiDto
    override fun convertToModel(objDto: CategoryApiDto): Category
}

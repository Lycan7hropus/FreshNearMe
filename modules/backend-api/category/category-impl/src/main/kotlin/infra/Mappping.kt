package infra

import domain.enitties.Category
import dto.CategoryApiDto
import dto.CategoryDto
import infra.mappers.CategoryApiDtoConverter
import infra.mappers.CategoryDtoConverter
import utils.toDto

internal fun Category.toDto(): CategoryDto {
    val dto = this.toDto(CategoryDtoConverter::class)
    return dto
}

internal fun Category.toApiDto(): CategoryApiDto {
    val apiDto = this.toDto(CategoryApiDtoConverter::class)
    return apiDto
}


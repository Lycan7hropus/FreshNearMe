package infra

import domain.enitties.Category
import dto.CategoryApiDto
import presentation.dto.CategoryDto
import infra.mappers.CategoryApiDtoConverter
import infra.mappers.CategoryDtoConverter

internal fun Category.toDto(): CategoryDto {
    return CategoryDtoConverter.INSTANCE.convertToDto(this)
}

internal fun Category.toApiDto(): CategoryApiDto {
    return CategoryApiDtoConverter.INSTANCE.convertToDto(this)
}

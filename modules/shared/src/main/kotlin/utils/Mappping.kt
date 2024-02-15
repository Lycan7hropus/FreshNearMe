package utils

import org.mapstruct.factory.Mappers
import kotlin.reflect.KClass


interface DtoDomainConverter<T: Any, X: Any> {
    fun convertToDto(obj: T): X
    fun convertToModel(objDto: X): T
}

inline fun <reified T : Any, reified X:  Any> T.toDto(converterClass: Class<out DtoDomainConverter<T, X>>): X {
    val converter = Mappers.getMapper(converterClass)
    return converter.convertToDto(this)
}

inline fun <reified T : Any, reified X:  Any> X.toModel(converterClass: KClass<out  DtoDomainConverter<T, X>>): T {
    val converter = Mappers.getMapper(converterClass.java)
    return converter.convertToModel(this)
}


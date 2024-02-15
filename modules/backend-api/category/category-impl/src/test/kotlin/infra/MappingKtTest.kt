//package infra
//
//import org.junit.jupiter.api.Assertions.assertEquals
//
//import org.junit.jupiter.api.Test
//import org.mapstruct.InheritInverseConfiguration
//import org.mapstruct.Mapper
//import org.mapstruct.Mapping
//import org.mapstruct.factory.Mappers
//import utils.*
//import java.time.LocalDate
//
//internal class MappingKtTest {
//    @Test
//    fun testToDto() {
//        val result = MyCategory("id", "name", "parentId", "path").toDto(MyCategoryDtoConverter::class)
//        assertEquals(MyCategoryDto("parentId", "name"), result)
//    }
//
////    @Test
////    fun testToApiDto() {
////        val myCategory = MyCategory("id", "name", "parentId", "path")
////        val converter: MyCategoryApiDtoConverter = Mappers.getMapper(MyCategoryApiDtoConverter::class.java)
////        converter.convertToDto()
////        assertEquals(MyCategoryApiDto("id", "name", "parentId", "path"), result)
////    }
//
//    @Test
//    fun testMapper() {
//
//        val category = MyCategory("id", "name", "parentId", "path").toDto(MyCategoryDtoConverter::class)
//        println(category)
//
////        val categoryDto = converter.convertToDto(category)
////        println(categoryDto)
//
////        val categoryModel = converter.convertToModel(categoryDto)
////        println(categoryModel)
//
//    }
//
//
//    data class MyCategoryDto(
//        val parentId: String,
//        val name: String
//    )
//
//
//    data class MyCategoryApiDto(
//        val id: String?,
//        val name: String?,
//        val parentId: String?,
//        val path: String?
//    )
//
//
//    data class MyCategory(val id: String?, val name: String?, val parentId: String?, val path: String?)
//
//
//
//    @Mapper
//    interface MyCategoryDtoConverter: DtoDomainConverter<MyCategory, MyCategoryDto> {
//        override fun convertToDto(obj: MyCategory): MyCategoryDto
//        override fun convertToModel(objDto: MyCategoryDto): MyCategory
//    }
//
//    @Mapper
//    interface MyCategoryApiDtoConverter : DtoDomainConverter<MyCategory, MyCategoryApiDto> {
//        override fun convertToDto(obj: MyCategory): MyCategoryApiDto
//        override fun convertToModel(objDto: MyCategoryApiDto): MyCategory
//    }
//
//    data class Person(var firstName: String, var lastName: String, var phoneNumber: String, var birthdate: LocalDate)
//    data class PersonDto(var firstName: String, var lastName: String, var phone: String, var birthdate: LocalDate)
//    @Mapper
//    interface PersonConverter {
//        @Mapping(source = "phoneNumber", target = "phone")
//        fun convertToDto(person: Person) : PersonDto
//
//        @InheritInverseConfiguration
//        fun convertToModel(personDto: PersonDto) : Person
//
//    }
//}
//

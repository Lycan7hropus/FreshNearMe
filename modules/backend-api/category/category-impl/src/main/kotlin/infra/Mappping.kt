package infra

//import dev.krud.shapeshift.ShapeShiftBuilder
//import domain.enitties.Category
//import dto.CategoryApiDto
//import dto.CategoryDto
//import org.jetbrains.annotations.ApiStatus
//import kotlin.reflect.full.createType
//
//internal fun Category.toDto(): CategoryDto {
//    val shapeShift = ShapeShiftBuilder().build()
//    return shapeShift.map(this, CategoryDto::class.java)
//}
//
//internal fun Category.toApiDto(): CategoryApiDto {
//    val shapeShift = ShapeShiftBuilder().build()
//    return shapeShift.map(this, CategoryApiDto::class.java)
//}
//
//
//inline fun <reified T> Any.transform(): T {
//    val shapeShift = ShapeShiftBuilder().build()
//    return shapeShift.map(T::class.createType())
//}
//package utils
//
//
//import org.mapstruct.factory.Mappers
//import kotlin.reflect.KClass
//
//
////inline fun <reified T : Any> Any.transform(): T {
////    val shapeShift = ShapeShiftBuilder().build()
////    val simpleEntityDisplay = T::class.java.getConstructor().newInstance()
////    return shapeShift.map(this, simpleEntityDisplay)
////}
////
////
////inline fun <reified T : Any, reified  X: Any> export(model: X): T {
////    val shapeShift = ShapeShiftBuilder().build()
////
////    return shapeShift.map<X,T>(model)
////}
////
////
////inline fun <reified T : Any, reified R : Any> Any.mapTo(converterClass: KClass<out DtoDomainConverter<T, R>>): R {
////    val converter: DtoDomainConverter<T, R> = Mappers.getMapper(converterClass.java)
////
////    if (this is T) {
////        return converter.convertToDto(this)
////    } else {
////        throw IllegalArgumentException("Konwerter ${converterClass.simpleName} nie może być zastosowany do ${this::class.simpleName}")
////    }
////}
//
//
//inline fun <reified T : Any, reified X:  Any> T.toDto(converterClass: Class<out DtoDomainConverter<T, X>>): X {
//    println("hej")
//
//    val converter = Mappers.getMapper(converterClass)
//    return converter.convertToDto(this)
//}
//
//inline fun <reified T : Any, reified X:  Any> X.toModel(converterClass: KClass<out  DtoDomainConverter<T, X>>): T {
//    val converter = Mappers.getMapper(converterClass.java)
//    return converter.convertToModel(this)
//}
//
//
//interface DtoDomainConverter<T: Any, X: Any> {
//    fun convertToDto(obj: T): X
//    fun convertToModel(objDto: X): T
//}
//

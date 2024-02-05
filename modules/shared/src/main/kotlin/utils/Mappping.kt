package utils

import dev.krud.shapeshift.ShapeShiftBuilder
import kotlin.reflect.full.createType



inline fun <reified T> Any.transform(): T {
    val shapeShift = ShapeShiftBuilder().build()
    return shapeShift.map(T::class.createType())
}
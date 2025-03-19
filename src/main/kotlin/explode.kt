package examples.general

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.explode

fun main() {
//   realiza un split a los valores en una lista que esten en una columna y los desestrucura en filas asignando cada valor
//    de la lista
    val df = dataFrameOf("name", "tags")(
        "Alice", listOf("Kotlin", "Java"),
        "Bob", listOf("Python", "C++", "Rust"),
        "Charlie", listOf("JavaScript"),
        "Dave", emptyList<String>() // Un usuario sin tags
    )

    println("Antes de explore \n$df ")

    val explodedDf = df.explode("tags")


    println("Despues de explode \n $explodedDf")
}
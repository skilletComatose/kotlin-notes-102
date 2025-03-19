package examples.general

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import kotlin.random.Random

const val moviePath = "src/main/resources/movies.csv"
const val HEAD: Int = 3
fun main() {



   val df: DataFrame<MovieSchema> =  DataFrame.readCSV(moviePath)
       .split(Movie::genres)
       .by("|").inplace()// se hace un split de los generos para tenerlos como lista
       .split(Movie::title)
       .by {
           listOf(
               "\\s*\\(\\d{4}\\)\\s*$".toRegex().replace(it, ""),
               "\\d{4}".toRegex().findAll(it).lastOrNull()?.value?.toIntOrNull() ?: -1
           )
       }
       .into(Movie::title, Movie::year) // guarda el primero elemento de la columna title en title y el segundo en year
       .explode(Movie::genres)// desestructura los generos
       .cast()

    val dfTest = df.add("rates") { Random.nextInt(1,5) }
//    "puedo agrupar dos columnas en una como un mapa"
    println(
        dfTest.group { year and genres}.into("info")
            .head(HEAD)
    )

    println(
        dfTest.groupBy { year and genres }
            .aggregate {
                rates.mean() into "mean rate"
                title.count() into "total movies"
                title into "names"
            }
            .sortByDesc { it["mean rate"]}
    )
}

data class Movie(
    val movieId: String,
    val title: String,
    val genres: String,
    val year: Int
)

@DataSchema
interface MovieSchema {
    val movieId: String
    val title: String
    val genres: String
    val year: Int
    val rates: Int
}


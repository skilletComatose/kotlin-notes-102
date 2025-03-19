package examples.general

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.pivot
import org.jetbrains.kotlinx.dataframe.api.values


fun main(){
    val df = dataFrameOf("city", "product", "sales")(
        "New York", "Laptop", 1000,
        "New York", "Phone", 800,
        "Los Angeles", "Laptop", 1200,
        "Los Angeles", "Phone", 900
    )

    println(df)
    println("*".repeat(20))

    println(
        df.pivot("product")
            .groupBy("city")
            .values()
    )
}
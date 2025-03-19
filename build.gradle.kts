plugins {
    kotlin("jvm") version "2.1.10"
    id("org.jetbrains.kotlinx.dataframe") version "0.15.0"
}

group = "examples.general"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
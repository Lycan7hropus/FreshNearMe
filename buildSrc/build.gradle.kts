
plugins {
    `kotlin-dsl`


}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    //kotlin("jvm")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.9.22")

    //kotlin("kapt")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:1.9.22")

    //id("io.ktor.plugin")
    implementation("io.ktor.plugin:io.ktor.plugin.gradle.plugin:2.3.8")

    //id("org.jetbrains.kotlin.plugin.serialization")
    implementation("org.jetbrains.kotlin.plugin.serialization:org.jetbrains.kotlin.plugin.serialization.gradle.plugin:1.9.22")

    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:5.2.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
//kotlin {
//    jvmToolchain(11)
//}
//

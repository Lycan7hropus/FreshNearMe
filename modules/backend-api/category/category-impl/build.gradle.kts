plugins {
    kotlin("jvm")
    id("myproject.java-conventions")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:backend-api:category:category-api"))
    implementation(project(":modules:shared"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
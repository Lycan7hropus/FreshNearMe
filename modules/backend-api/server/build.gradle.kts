plugins {
    kotlin("jvm")
    application
    id("myproject.java-conventions")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:shared"))

    implementation(project(":modules:backend-api:category:category-impl"))
    implementation(project(":modules:backend-api:auth:auth-impl"))
    implementation(project(":modules:backend-api:user:user-impl"))
    implementation(project(":modules:backend-api:offer:offer-impl"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:common"))
    implementation(project(":modules:category:category-impl"))
    implementation(project(":modules:offer:offer-impl"))
    implementation(project(":modules:offer:offer-api"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
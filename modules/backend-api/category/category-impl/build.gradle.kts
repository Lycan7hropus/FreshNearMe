plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":modules:backend-api:category:category-api"))
    implementation(project(":modules:backend-api:common"))

    implementation(project(":modules:shared"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}
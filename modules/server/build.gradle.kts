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
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
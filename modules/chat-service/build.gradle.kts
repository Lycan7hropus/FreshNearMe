plugins {
    kotlin("jvm")

    application
    id("myproject.java-conventions")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:shared"))

    implementation("io.ktor:ktor-server-websockets-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

plugins {
    kotlin("jvm")
    id("myproject.java-conventions")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":modules:shared"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
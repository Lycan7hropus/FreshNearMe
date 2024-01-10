plugins {
    kotlin("jvm")
}

    group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:user:user-api"))
    implementation(project(":modules:offer:offer-api"))
    implementation(project(":modules:common"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
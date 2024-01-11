plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:backend-api:user:user-api"))
    implementation(project(":modules:backend-api:offer:offer-api"))
    implementation(project(":modules:backend-api:common"))
    implementation(project(":modules:shared"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
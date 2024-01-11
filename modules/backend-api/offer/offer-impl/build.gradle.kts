plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:shared"))

    implementation(project(":modules:backend-api:category:category-api"))
    implementation(project(":modules:backend-api:offer:offer-api"))
    implementation(project(":modules:backend-api:common"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
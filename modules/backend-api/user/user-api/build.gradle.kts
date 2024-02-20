plugins {
    id("myproject.java-conventions")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
//    implementation(project(":modules:backend-api:offer:offer-api"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.krud:shapeshift:0.8.0")
    implementation(project(":modules:backend-api:category:category-api"))
    implementation(project(":modules:backend-api:common"))
    implementation(project(":modules:shared"))



}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_ktor: String = "3.5.1"
val mapStructVersion = "latest.release"


plugins {
    java
    checkstyle

    kotlin("jvm")
    kotlin("kapt")
    id("io.ktor.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")

    id("com.github.spotbugs")
}


group = "com.example"
version = "1.0"

tasks.test {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}


dependencies {
    implementation( "com.github.javafaker:javafaker:1.0.2")
    testImplementation("junit:junit:4.13")

    //mapstruct
    implementation("org.mapstruct:mapstruct:$mapStructVersion")
    "kapt"("org.mapstruct:mapstruct-processor:$mapStructVersion")

    //CORS
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    //GSON
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    //ouath
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")

    //date and time
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    //konform
    implementation("io.konform:konform:0.4.0")

    //MONGODB
    implementation("io.ktor:ktor-server-netty:2.2.3") // latest
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("org.litote.kmongo:kmongo-coroutine:4.8.0") // latest:

    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_ktor")
    // SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_ktor")

    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.testng:testng:7.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}
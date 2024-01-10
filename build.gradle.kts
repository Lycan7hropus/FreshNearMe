import io.gitlab.arturbosch.detekt.Detekt

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_ktor: String = "3.5.1"

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version("1.23.3")
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

detekt {
    toolVersion = "1.23.3"
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}
// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

dependencies {

}


subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.ktor.plugin")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    application {
        mainClass.set("io.ktor.server.netty.EngineMain")

        val isDevelopment: Boolean = project.ext.has("development")
        applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
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
        implementation("org.testng:testng:7.1.0")
        implementation("org.testng:testng:7.1.0")
        testImplementation("io.ktor:ktor-server-tests-jvm")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    }
}
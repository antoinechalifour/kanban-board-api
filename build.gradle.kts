import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val junit_version: String by project
val assertj_version: String by project

plugins {
    application
    kotlin("jvm") version "1.4.32"
}

group = "dev.antoinechalifour"
version = "0.0.1"

application {
    mainClass.set("dev.antoinechalifour.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
    testImplementation("org.assertj:assertj-core:$assertj_version")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
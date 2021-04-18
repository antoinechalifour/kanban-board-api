package dev.antoinechalifour

import dev.antoinechalifour.plugins.configureHTTP
import dev.antoinechalifour.plugins.configureKoin
import dev.antoinechalifour.plugins.configureMonitoring
import dev.antoinechalifour.plugins.configureRouting
import io.ktor.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureRouting()
    configureHTTP()
    configureMonitoring()
}
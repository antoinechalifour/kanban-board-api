package dev.antoinechalifour

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.antoinechalifour.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureMonitoring()
    }.start(wait = true)
}

package dev.antoinechalifour.acceptance

import dev.antoinechalifour.module
import io.ktor.application.*
import io.ktor.server.testing.*

fun <R> withApplicationModule(test: TestApplicationEngine.() -> R): R = withTestApplication(Application::module, test)
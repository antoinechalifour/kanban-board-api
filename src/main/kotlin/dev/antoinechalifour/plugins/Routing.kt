package dev.antoinechalifour.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import dev.antoinechalifour.application.boardsEndpoints
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        boardsEndpoints()
    }
}

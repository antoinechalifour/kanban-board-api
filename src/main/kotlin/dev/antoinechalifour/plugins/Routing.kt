package dev.antoinechalifour.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import dev.antoinechalifour.application.boardsEndpoints
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*

data class CreateBoardResponse(val id: String, val name: String)

fun Application.configureRouting() {
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        boardsEndpoints()
    }
}

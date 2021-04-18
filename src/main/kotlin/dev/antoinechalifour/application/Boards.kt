package dev.antoinechalifour.application

import dev.antoinechalifour.domain.CreateBoard
import dev.antoinechalifour.domain.CreateBoardCommand
import dev.antoinechalifour.plugins.CreateBoardResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.boardsEndpoints() {
    val createBoard by inject<CreateBoard>()

    post("/v1/boards") {
        call.apply {
            val command = receive<CreateBoardCommand>()

            createBoard(command)

            response.status(HttpStatusCode.Created)
            respond(CreateBoardResponse(command.id, command.name))
        }
    }
}
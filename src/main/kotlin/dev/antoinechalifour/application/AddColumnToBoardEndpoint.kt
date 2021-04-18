package dev.antoinechalifour.application

import dev.antoinechalifour.domain.AddColumnToBoard
import dev.antoinechalifour.domain.AddColumnToBoardCommand
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Boards
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.addColumnToBoardEndpoint() {
    val addColumnToBoard by inject<AddColumnToBoard>()
    val boards by inject<Boards>()

    post("/v1/boards/columns") {
        val command = call.receive<AddColumnToBoardCommand>()
        addColumnToBoard(command)

        call.apply {
            val board = boards.ofId(BoardId(command.boardId))

            response.status(HttpStatusCode.Created)
            respond(BoardResponse.of(board))
        }
    }
}
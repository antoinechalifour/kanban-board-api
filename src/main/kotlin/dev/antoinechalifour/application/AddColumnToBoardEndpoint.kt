package dev.antoinechalifour.application

import dev.antoinechalifour.application.view.BoardViews
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
    val boardViews by inject<BoardViews>()

    post("/v1/boards/columns") {
        val command = call.receive<AddColumnToBoardCommand>()
        addColumnToBoard(command)

        call.apply {
            val boardId = BoardId(command.boardId)

            response.status(HttpStatusCode.Created)
            respond(boardViews.forBoard(boardId))
        }
    }
}
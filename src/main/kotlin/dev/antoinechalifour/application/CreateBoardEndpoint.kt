package dev.antoinechalifour.application

import dev.antoinechalifour.application.view.BoardViews
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Boards
import dev.antoinechalifour.domain.CreateBoard
import dev.antoinechalifour.domain.CreateBoardCommand
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.createBoardEndpoint() {
    val createBoard by inject<CreateBoard>()
    val boardViews by inject<BoardViews>()

    post("/v1/boards") {
        val command = call.receive<CreateBoardCommand>()
        createBoard(command)

        call.apply {
            val boardId = BoardId(command.id)

            response.status(HttpStatusCode.Created)
            respond(boardViews.forBoard(boardId))
        }
    }
}
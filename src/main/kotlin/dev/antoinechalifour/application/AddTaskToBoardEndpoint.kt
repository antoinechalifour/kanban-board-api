package dev.antoinechalifour.application

import dev.antoinechalifour.application.view.BoardViews
import dev.antoinechalifour.domain.AddTaskToBoard
import dev.antoinechalifour.domain.AddTaskToBoardCommand
import dev.antoinechalifour.domain.BoardId
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.addTaskToBoardEndpoint() {
    val addTaskToBoard by inject<AddTaskToBoard>()
    val boardViews by inject<BoardViews>()

    post("/v1/boards/tasks") {
        val command = call.receive<AddTaskToBoardCommand>()

        addTaskToBoard(command)

        call.apply {
            val boardId = BoardId(command.boardId)

            response.status(HttpStatusCode.Created)
            respond(boardViews.forBoard(boardId))
        }
    }
}
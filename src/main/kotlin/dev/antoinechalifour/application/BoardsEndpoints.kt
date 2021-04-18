package dev.antoinechalifour.application

import io.ktor.routing.*

fun Route.boardsEndpoints() {
    createBoardEndpoint()
    addColumnToBoardEndpoint()
    addTaskToBoardEndpoint()
}
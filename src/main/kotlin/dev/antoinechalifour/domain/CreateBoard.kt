package dev.antoinechalifour.domain

class CreateBoard (private val boards: Boards) {
    operator fun invoke(command: CreateBoardCommand) {
        val board = Board.create(command)

        boards.save(board)
    }
}
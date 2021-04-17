package dev.antoinechalifour.domain

class CreateBoard(private val boards: Boards) {
    operator fun invoke(command: CreateBoardCommand) {
        boards.save(Board.create(command))
    }
}
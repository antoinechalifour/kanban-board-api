package dev.antoinechalifour.domain

class Board(val id: BoardId, val name: String) {
    companion object Factory {
        fun create(command: CreateBoardCommand) = Board(BoardId(command.id), command.name)
    }
}

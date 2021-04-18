package dev.antoinechalifour.domain

class AddColumnToBoard(private val boards: Boards) {
    operator fun invoke(command: AddColumnToBoardCommand) {
        val board = boards.ofId(BoardId(command.boardId))
            .apply { addColumn(ColumnId(command.id), command.name) }

        boards.save(board)
    }
}
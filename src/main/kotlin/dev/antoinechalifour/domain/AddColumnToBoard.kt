package dev.antoinechalifour.domain

class AddColumnToBoard(private val boards: Boards) {
    operator fun invoke(command: AddColumnToBoardCommand) =
        boards.ofId(BoardId(command.boardId)).run {
            addColumn(command.columnId, command.columnName)
            boards.save(this)
        }
}
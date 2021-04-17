package dev.antoinechalifour.domain

class Board(
    val id: BoardId,
    val name: String) {
    val columns: MutableList<Column> = mutableListOf()

    companion object Factory {
        fun create(command: CreateBoardCommand) = Board(BoardId(command.id), command.name)
    }

    fun addColumn(columnId: String, columnName: String) {
        columns.add(Column(ColumnId(columnId), columnName))
    }
}

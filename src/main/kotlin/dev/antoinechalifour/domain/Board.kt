package dev.antoinechalifour.domain

class Board(
    private val id: BoardId,
    private val name: String
) {
    private val columns: MutableList<Column> = mutableListOf()

    companion object Factory {
        fun create(command: CreateBoardCommand) = Board(BoardId(command.id), command.name)
    }

    constructor(memento: BoardSnapshot) : this(BoardId(memento.id), memento.name) {
        columns.apply {
            clear()
            addAll(memento.columns.map { Column(it) })
        }
    }

    fun addColumn(columnId: ColumnId, columnName: String) {
        columns.add(Column(columnId, columnName))
    }

    fun addTaskToColumn(columnId: ColumnId, taskId: TaskId, taskContent: String) =
        columns.find { it.isIdentifiedBy(columnId) }
            ?.let { Task(taskId, id, columnId, taskContent) }
            ?: throw NoSuchElementException("Column \"$columnId\"")

    fun snapshot() = BoardSnapshot(id.toString(), name, ColumnSnapshot.of(columns))
}

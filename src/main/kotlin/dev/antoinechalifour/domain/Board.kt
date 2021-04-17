package dev.antoinechalifour.domain

class Board(
    private val id: BoardId,
    private val name: String
) {
    private val columns: MutableList<Column> = mutableListOf()

    constructor(memento: Memento) : this(memento.id, memento.name) {
        columns.apply {
            clear()
            addAll(memento.columns)
        }
    }

    companion object Factory {
        fun create(command: CreateBoardCommand) = Board(BoardId(command.id), command.name)
    }

    fun addColumn(columnId: ColumnId, columnName: String) {
        columns.add(Column(columnId, columnName))
    }

    fun addTaskToColumn(columnId: ColumnId, taskId: TaskId, taskContent: String): Task {
        return columns.find { it.id == columnId }
            ?.let { Task(taskId, id, columnId, taskContent) }
            ?: throw NoSuchElementException("Column \"$columnId\"")
    }

    fun state() = Memento(id, name, columns)

    class Memento(val id: BoardId, val name: String, val columns: List<Column>)
}

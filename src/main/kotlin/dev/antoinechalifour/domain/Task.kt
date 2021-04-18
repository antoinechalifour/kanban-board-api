package dev.antoinechalifour.domain

class Task(
    private val id: TaskId,
    private val boardId: BoardId,
    private val columnId: ColumnId,
    private val content: String
) {
    fun snapshot() = TaskSnapshot(id.toString(), content, boardId.toString(), columnId.toString())
}

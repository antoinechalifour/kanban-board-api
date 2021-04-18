package dev.antoinechalifour.domain

class Task(
    val id: TaskId,
    val boardId: BoardId,
    val columnId: ColumnId,
    val content: String
)

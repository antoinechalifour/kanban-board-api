package dev.antoinechalifour.domain

interface Tasks {
    fun ofBoard(boardId: BoardId): List<Task>
    fun save(task: Task)
}

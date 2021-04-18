package dev.antoinechalifour.domain

interface Tasks {
    fun save(task: Task)
    fun ofId(taskId: TaskId): Task
    fun ofBoardId(boardId: BoardId): List<Task>
}

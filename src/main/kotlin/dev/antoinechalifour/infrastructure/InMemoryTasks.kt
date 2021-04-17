package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Task
import dev.antoinechalifour.domain.TaskId
import dev.antoinechalifour.domain.Tasks

class InMemoryTasks : Tasks {
    private val database = HashMap<TaskId, Task>()

    override fun ofBoard(boardId: BoardId) = database
        .filter { it.value.boardId == boardId }
        .map { it.value }

    override fun save(task: Task) {
        database[task.id] = task
    }
}

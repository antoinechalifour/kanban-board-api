package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Task
import dev.antoinechalifour.domain.TaskId
import dev.antoinechalifour.domain.Tasks

class InMemoryTasks : Tasks {
    private val database = HashMap<TaskId, Task>()

    override fun save(task: Task) {
        database[task.snapshot().id.asTaskId()] = task
    }

    override fun ofId(taskId: TaskId) = database[taskId] ?: throw NoSuchElementException("Task \"$taskId\"")

    override fun ofBoardId(boardId: BoardId): List<Task> = database.values
        .filter { it.snapshot().boardId == boardId.toString() }
}



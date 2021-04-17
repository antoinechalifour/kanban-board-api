package dev.antoinechalifour.domain

class AddTaskToBoard(
    private val boards: Boards,
    private val tasks: Tasks
) {
    operator fun invoke(command: AddTaskCommand) = boards.ofId(BoardId(command.boardId)).run {
        val task = addTaskToColumn(
            ColumnId(command.columnId),
            TaskId(command.taskId),
            command.taskContent
        )

        tasks.save(task)
    }
}
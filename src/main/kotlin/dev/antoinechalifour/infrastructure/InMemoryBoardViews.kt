package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.application.view.BoardView
import dev.antoinechalifour.application.view.BoardViews
import dev.antoinechalifour.application.view.ColumnView
import dev.antoinechalifour.application.view.TaskView
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Boards
import dev.antoinechalifour.domain.Tasks

class InMemoryBoardViews(
    private val boards: Boards,
    private val tasks: Tasks
) : BoardViews {
    override fun forBoard(boardId: BoardId): BoardView {
        val board = boards.ofId(boardId)
        val tasks = tasks.ofBoardId(boardId)

        val (id, name, columns) = board.snapshot()
        val columnsViews = columns.map { column ->
            val columnTasks = tasks
                .map { task -> task.snapshot() }
                .filter { task -> task.columnId == column.id }
                .map { task -> TaskView(task.id, task.content) }

            ColumnView(column.id, column.name, columnTasks)
        }

        return BoardView(id, name, columnsViews)
    }

}
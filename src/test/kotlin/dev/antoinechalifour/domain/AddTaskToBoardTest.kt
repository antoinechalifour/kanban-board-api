package dev.antoinechalifour.domain

import dev.antoinechalifour.aBoard
import dev.antoinechalifour.infrastructure.InMemoryBoards
import dev.antoinechalifour.infrastructure.InMemoryTasks
import dev.antoinechalifour.save
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AddTaskToBoardTest {
    lateinit var boards: Boards
    lateinit var tasks: Tasks
    lateinit var addTaskToBoard: AddTaskToBoard

    @BeforeEach
    internal fun setUp() {
        boards = InMemoryBoards()
        tasks = InMemoryTasks()
        addTaskToBoard = AddTaskToBoard(boards, tasks)
    }

    @Test
    internal fun `creates a new task for the column of the board`() {
        // Arrange
        boards.save(aBoard().withId("board-1").withColumns("column-1", "column-2"))

        val command = AddTaskToBoardCommand(
            "task-1",
            "board-1",
            "column-2",
            "some content")

        // Act
        addTaskToBoard(command)

        // Assert
        val snapshot = tasks.ofId(TaskId("task-1")).snapshot()
        Assertions.assertThat(snapshot.content).isEqualTo("some content")
    }

    @Test
    internal fun `cannot add task on missing board`() {
        // Arrange
        val command = AddTaskToBoardCommand(
            "task-1",
            "missing-board-id",
            "column-2",
            "some content")

        // Act
        Assertions.assertThatThrownBy { addTaskToBoard(command) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Board \"missing-board-id\"")
    }

    @Test
    internal fun `cannot add task on missing column`() {
        // Arrange
        boards.save(aBoard().withId("board-1"))

        val command = AddTaskToBoardCommand(
            "task-1",
            "board-1",
            "missing-column-id",
            "some content")

        // Act
        Assertions.assertThatThrownBy { addTaskToBoard(command) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Column \"missing-column-id\"")
    }
}


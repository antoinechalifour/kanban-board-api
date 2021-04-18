package dev.antoinechalifour.domain

import dev.antoinechalifour.infrastructure.InMemoryBoards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AddColumnToBoardTest {
    lateinit var addColumnToBoard: AddColumnToBoard
    lateinit var boards: Boards

    @BeforeEach
    internal fun setUp() {
        boards = InMemoryBoards()
        addColumnToBoard = AddColumnToBoard(boards)
    }

    @Test
    internal fun `adds a column to the board`() {
        // Arrange
        val boardId = BoardId("board-1")
        boards.save(Board(boardId, "Board name"))
        val command1 = AddColumnToBoardCommand("column-1", boardId.toString(), "Column 1 name")
        val command2 = AddColumnToBoardCommand("column-2", boardId.toString(), "Column 2 name")

        // Act
        addColumnToBoard(command1)
        addColumnToBoard(command2)

        // Assert
        val snapshot = boards.ofId(boardId).snapshot()
        Assertions.assertThat(snapshot.columns)
            .usingRecursiveComparison()
            .isEqualTo(
                listOf(
                    Column(ColumnId("column-1"), "Column 1 name"),
                    Column(ColumnId("column-2"), "Column 2 name")
                )
            )
    }

    @Test
    internal fun `cannot add a column to a missing board`() {
        // Arrange
        val command = AddColumnToBoardCommand(
            "column-1",
            "missing-board-id",
            "Column 1 name"
        )

        // Act
        Assertions.assertThatThrownBy { addColumnToBoard(command) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Board \"missing-board-id\"")
    }
}
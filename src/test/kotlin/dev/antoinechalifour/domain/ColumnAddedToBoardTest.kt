package dev.antoinechalifour.domain

import dev.antoinechalifour.infrastructure.InMemoryBoards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ColumnAddedToBoardTest {
    lateinit var addColumnToBoard: AddColumnToBoard
    lateinit var boards: Boards
    var boardId: BoardId? = null

    @BeforeEach
    internal fun setUp() {
        boardId = BoardId("board-1")
        boards = InMemoryBoards()
        addColumnToBoard = AddColumnToBoard(boards)

        val board = Board(boardId!!, "Board name")
        boards.save(board)
    }

    @Test
    internal fun `adds a column to the board`() {
        // Arrange
        val command1 = AddColumnToBoardCommand(boardId!!.id, "column-1", "Column 1 name")
        val command2 = AddColumnToBoardCommand(boardId!!.id, "column-2", "Column 2 name")

        // Act
        addColumnToBoard(command1)
        addColumnToBoard(command2)

        // Assert
        val board = boards.ofId(boardId!!)
        Assertions.assertThat(board.columns)
            .usingRecursiveComparison()
            .isEqualTo(
                listOf(
                    Column(ColumnId("column-1"), "Column 1 name"),
                    Column(ColumnId("column-2"), "Column 2 name")
                )
            )
    }
}
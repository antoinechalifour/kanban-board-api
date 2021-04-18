package dev.antoinechalifour.domain

import dev.antoinechalifour.infrastructure.InMemoryBoards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CreateBoardTest {
    lateinit var boards: Boards
    lateinit var createBoard: CreateBoard

    @BeforeEach
    internal fun setUp() {
        boards = InMemoryBoards()
        createBoard = CreateBoard(boards)
    }

    @Test
    internal fun `creates a new board`() {
        // Arrange
        val command = CreateBoardCommand("new-board-id", "Board Name")

        // Act
        createBoard(command)

        // Assert
        val state = boards.ofId(BoardId("new-board-id")).snapshot()
        Assertions.assertThat(state.id).isEqualTo("new-board-id")
        Assertions.assertThat(state.name).isEqualTo("Board Name")
        Assertions.assertThat(state.columns).isEqualTo(emptyList<Column>())
    }
}
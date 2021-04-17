package dev.antoinechalifour.domain

import dev.antoinechalifour.infrastructure.InMemoryBoards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BoardCreatedTest {
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
        val id = "new-board-id"
        val name = "Board Name"
        val command = CreateBoardCommand(id, name)

        // Act
        createBoard(command)

        // Assert
        val board = boards.ofId(BoardId(id))
        Assertions.assertThat(board.id).isEqualTo(BoardId(id))
        Assertions.assertThat(board.name).isEqualTo(name)
    }
}
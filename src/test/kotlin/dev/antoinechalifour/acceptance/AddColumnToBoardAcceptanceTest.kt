package dev.antoinechalifour.acceptance

import dev.antoinechalifour.aBoard
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Boards
import dev.antoinechalifour.domain.Column
import dev.antoinechalifour.domain.ColumnId
import dev.antoinechalifour.save
import io.ktor.http.*
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class AddColumnToBoardAcceptanceTest : KoinTest {
    private val boards by inject<Boards>()

    @Test
    internal fun `adds a column to the board`(): Unit = withApplicationModule {
        // Arrange
        boards.save(aBoard().withId("board-1"))
        val requestBody = """
            {
              "id": "column-1",
              "boardId": "board-1",
              "name": "My first column"
            }
        """.trimIndent()

        // Act
        handleRequest {
            method = HttpMethod.Post
            uri = "/v1/boards/columns"
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(requestBody)
        }.apply {
            // Assert API response
            val responseBody = """
                {"id":"board-1","name":"Board name","columns":[{"id":"column-1","name":"My first column"}]}
            """.trimIndent()
            Assertions.assertThat(response.status()).isEqualTo(HttpStatusCode.Created)
            Assertions.assertThat(response.content).isEqualTo(responseBody)

            // Assert application state
            val board = boards.ofId(BoardId("board-1")).snapshot()
            Assertions.assertThat(board.columns)
                .usingRecursiveComparison()
                .isEqualTo(listOf(Column(
                    ColumnId("column-1"),
                    "My first column"
                )))
        }
    }
}
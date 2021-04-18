package dev.antoinechalifour.acceptance

import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.BoardSnapshot
import dev.antoinechalifour.domain.Boards
import io.ktor.http.*
import io.ktor.server.testing.*
import io.netty.handler.codec.http.HttpHeaderValues
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.inject

internal class CreateBoardAcceptanceTest : KoinTest {
    private val boards by inject<Boards>()

    @Test
    fun `Creates a new board`(): Unit = withApplicationModule {
        // Arrange
        val requestBody = """
            {
              "id": "board-1",
              "name": "My first board"
            }
        """.trimIndent()

        // Act
        handleRequest {
            method = HttpMethod.Post
            uri = "/v1/boards"
            addHeader(HttpHeaders.ContentType, HttpHeaderValues.APPLICATION_JSON.toString())
            setBody(requestBody)
        }.apply {
            // Assert API response
            val responseBody = """
                {"id":"board-1","name":"My first board","columns":[]}
            """.trimIndent()
            Assertions.assertThat(response.status()).isEqualTo(HttpStatusCode.Created)
            Assertions.assertThat(response.content).isEqualTo(responseBody)

            // Assert application state
            val newBoard = boards.ofId(BoardId("board-1")).snapshot()
            val expectedNewBoard = BoardSnapshot(
                "board-1",
                "My first board",
                emptyList()
            )
            Assertions.assertThat(newBoard)
                .usingRecursiveComparison()
                .isEqualTo(expectedNewBoard)
        }
    }
}
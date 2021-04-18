package dev.antoinechalifour.acceptance

import dev.antoinechalifour.aBoard
import dev.antoinechalifour.domain.Boards
import dev.antoinechalifour.save
import io.ktor.http.*
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.inject

internal class AddTaskToBoardAcceptanceTest : KoinTest {
    private val boards by inject<Boards>()

    @Test
    internal fun `add a task to the board`(): Unit = withApplicationModule {
        // Arrange
        boards.save(aBoard().withId("board-2").withColumns("column-1", "column-2"))
        val requestBody = """
            {
                "id": "task-1",
                "boardId": "board-2",
                "columnId": "column-1",
                "content": "some content"
            }
        """.trimIndent()

        // Act
        handleRequest {
            method = HttpMethod.Post
            uri = "/v1/boards/tasks"
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(requestBody)
        }.apply {
            // Assert API response
            val responseBody = """
                {"id":"board-2","name":"Board name","columns":[{"id":"column-1","name":"Column name","tasks":[{"id":"task-1","content":"some content"}]},{"id":"column-2","name":"Column name","tasks":[]}]}
            """.trimIndent()
            Assertions.assertThat(response.status()).isEqualTo(HttpStatusCode.Created)
            Assertions.assertThat(response.content).isEqualTo(responseBody)
        }
    }

    // TODO: add task to missing board
    // TODO: add task to missing column
}
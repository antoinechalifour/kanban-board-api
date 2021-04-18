package dev.antoinechalifour

import dev.antoinechalifour.domain.Board
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.BoardSnapshot
import dev.antoinechalifour.domain.Column
import dev.antoinechalifour.domain.ColumnId
import dev.antoinechalifour.domain.ColumnSnapshot

class BoardTestBuilder {
    private var id = BoardId("board-1")
    private var name = "Board name"
    private var columns = listOf<Column>()

    fun withId(boardId: String) = apply { this.id = BoardId(boardId) }

    fun withColumns(vararg columns: String) = apply {
        this.columns = columns.map {
            Column(
                ColumnId(it),
                "Column name"
            )
        }
    }

    fun build() = Board(
        BoardSnapshot(
            id.toString(),
            name,
            ColumnSnapshot.of(columns)
        )
    )
}

fun aBoard() = BoardTestBuilder()
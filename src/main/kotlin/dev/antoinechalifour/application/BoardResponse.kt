package dev.antoinechalifour.application

import dev.antoinechalifour.domain.Board
import dev.antoinechalifour.domain.ColumnSnapshot

data class BoardResponse(
    val id: String,
    val name: String,
    val columns: List<ColumnResponse>
) {
    companion object Factory {
        fun of(board: Board) = board.snapshot().run {
            BoardResponse(
                id,
                name,
                columns.map { ColumnResponse.of(it) }
            )
        }
    }

    data class ColumnResponse(
        val id: String,
        val name: String
    ) {
        companion object Factory {
            fun of(column: ColumnSnapshot) = ColumnResponse(column.id, column.name)
        }
    }
}

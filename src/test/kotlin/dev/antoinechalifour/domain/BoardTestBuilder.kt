package dev.antoinechalifour.domain

class BoardTestBuilder {
    private var id = BoardId("board-1")
    private var name = "Board name"
    private var columns = listOf<Column>()

    fun withId(boardId: String) = apply { this.id = BoardId(boardId) }

    fun withColumns(vararg columns: String) = apply {
        this.columns = columns.map { Column(
            ColumnId(it),
            "Column name")
        }
    }

    fun build() = Board(Board.Memento(
        id,
        name,
        columns
    ))
}

fun aBoard() = BoardTestBuilder()
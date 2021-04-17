package dev.antoinechalifour.domain

interface Boards {
    fun ofId(boardId: BoardId): Board
    fun save(board: Board)
}

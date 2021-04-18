package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.domain.Board
import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.Boards

class InMemoryBoards : Boards {
    private val database = HashMap<BoardId, Board>()

    override fun ofId(boardId: BoardId) = database[boardId] ?: throw NoSuchElementException("Board \"$boardId\"")
    override fun save(board: Board) {
        database[board.snapshot().id.asBoardId()] = board
    }
}


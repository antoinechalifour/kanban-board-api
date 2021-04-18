package dev.antoinechalifour.application.view

import dev.antoinechalifour.domain.BoardId

interface BoardViews {
    fun forBoard(boardId: BoardId): BoardView
}
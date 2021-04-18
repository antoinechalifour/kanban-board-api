package dev.antoinechalifour

import dev.antoinechalifour.domain.Boards

fun Boards.save(board: BoardTestBuilder) = this.save(board.build())
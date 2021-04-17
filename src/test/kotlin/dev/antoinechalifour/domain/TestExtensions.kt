package dev.antoinechalifour.domain

fun Boards.save(board: BoardTestBuilder) = this.save(board.build())
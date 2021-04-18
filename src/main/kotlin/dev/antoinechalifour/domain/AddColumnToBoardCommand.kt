package dev.antoinechalifour.domain

data class AddColumnToBoardCommand(
    val id: String,
    val boardId: String,
    val name: String
)

package dev.antoinechalifour.domain

data class AddTaskToBoardCommand(
    val id: String,
    val boardId: String,
    val columnId: String,
    val content: String
)

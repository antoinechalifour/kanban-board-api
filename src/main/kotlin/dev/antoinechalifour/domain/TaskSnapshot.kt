package dev.antoinechalifour.domain

data class TaskSnapshot(
    val id: String,
    val content: String,
    val boardId: String,
    val columnId: String
)

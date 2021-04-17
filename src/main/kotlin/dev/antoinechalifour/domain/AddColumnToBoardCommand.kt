package dev.antoinechalifour.domain

data class AddColumnToBoardCommand (
    val boardId: String,
    val columnId: String,
    val columnName: String)

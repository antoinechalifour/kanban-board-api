package dev.antoinechalifour.domain

data class AddTaskToBoardCommand(val boardId: String, val columnId: String, val taskId: String, val taskContent: String)

package dev.antoinechalifour.domain

data class AddTaskCommand(val boardId: String, val columnId: String, val taskId: String, val taskContent: String)

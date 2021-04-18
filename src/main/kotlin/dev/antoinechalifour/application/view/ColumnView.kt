package dev.antoinechalifour.application.view

data class ColumnView(
    val id: String,
    val name: String,
    val tasks: List<TaskView>
)
package dev.antoinechalifour.application.view

data class BoardView(
    val id: String,
    val name: String,
    val columns: List<ColumnView>
)
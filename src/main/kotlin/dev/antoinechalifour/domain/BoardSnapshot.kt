package dev.antoinechalifour.domain

data class BoardSnapshot(
    val id: String,
    val name: String,
    val columns: List<ColumnSnapshot>
)
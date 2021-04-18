package dev.antoinechalifour.domain

data class ColumnSnapshot(
    val id: String,
    val name: String
) {
    companion object Factory {
        fun of(columns: List<Column>) = columns.map { it.snapshot() }
    }
}
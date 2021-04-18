package dev.antoinechalifour.domain

class Column(
    private val id: ColumnId,
    private val name: String
) {
    constructor(memento: ColumnSnapshot) : this(
        ColumnId(memento.id),
        memento.name
    )

    fun isIdentifiedBy(otherId: ColumnId) = id == otherId

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        return id == (other as Column).id
    }

    override fun hashCode() = id.hashCode()

    fun snapshot() = ColumnSnapshot(id.toString(), name)
}

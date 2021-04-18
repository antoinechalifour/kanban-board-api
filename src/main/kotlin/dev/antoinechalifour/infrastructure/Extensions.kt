package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.domain.BoardId
import dev.antoinechalifour.domain.TaskId

fun String.asBoardId() = BoardId(this)
fun String.asTaskId() = TaskId(this)
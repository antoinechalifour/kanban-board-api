package dev.antoinechalifour.infrastructure

import dev.antoinechalifour.domain.BoardId

fun String.asBoardId() = BoardId(this)
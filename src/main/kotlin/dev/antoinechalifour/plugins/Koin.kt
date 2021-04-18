package dev.antoinechalifour.plugins

import dev.antoinechalifour.application.view.BoardViews
import dev.antoinechalifour.domain.AddColumnToBoard
import dev.antoinechalifour.domain.AddTaskToBoard
import dev.antoinechalifour.domain.Boards
import dev.antoinechalifour.domain.CreateBoard
import dev.antoinechalifour.domain.Tasks
import dev.antoinechalifour.infrastructure.InMemoryBoardViews
import dev.antoinechalifour.infrastructure.InMemoryBoards
import dev.antoinechalifour.infrastructure.InMemoryTasks
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

val appModule = module {
    // Domain
    single { CreateBoard(get()) }
    single { AddColumnToBoard(get()) }
    single { AddTaskToBoard(get(), get()) }

    // Infrastructure
    single<Boards> { InMemoryBoards() }
    single<Tasks> { InMemoryTasks() }
    single<BoardViews> { InMemoryBoardViews(get(), get()) }
}

fun Application.configureKoin() = install(Koin) {
    modules(appModule)
}
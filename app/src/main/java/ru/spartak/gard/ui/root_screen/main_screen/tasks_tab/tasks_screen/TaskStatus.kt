package ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen

import androidx.compose.ui.graphics.Color
import ru.spartak.gard.ui.theme.Pink500
import ru.spartak.gard.ui.theme.Success500

sealed class TaskStatus(val name: String, val color: Color) {
    object Active : TaskStatus(name = "Active", color = Pink500)
    object Completed : TaskStatus(name = "Completed", color = Success500)
}
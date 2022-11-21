package ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.tasks_screen

import androidx.compose.ui.graphics.Color
import ru.spartak.gard.ui.theme.Tertiary200
import ru.spartak.gard.ui.theme.Tertiary400
import ru.spartak.gard.ui.theme.Tertiary50

sealed class TaskPeriodicity(val name:String ,val color: Color) {
    object Daily:TaskPeriodicity(name="Daily",color= Tertiary50)
    object Weekly:TaskPeriodicity(name="Weekly",color= Tertiary200)
    object Monthly:TaskPeriodicity(name="Monthly",color= Tertiary400)
}
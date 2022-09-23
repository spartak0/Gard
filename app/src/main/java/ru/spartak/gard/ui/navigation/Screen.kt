package ru.spartak.gard.ui.navigation

sealed class Screen(val route: String) {
    object LevelsScreen : Screen("levels_screen")
}
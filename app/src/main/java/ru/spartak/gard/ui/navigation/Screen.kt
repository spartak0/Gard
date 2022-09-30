package ru.spartak.gard.ui.navigation

sealed class Screen(val route: String) {
    object LevelScreen : Screen(route = "level_screen")
    object ProfileScreen : Screen("profile_screen")
}
package ru.spartak.gard.ui.navigation

sealed class Screen(val route: String) {
    object LevelScreen : Screen(route = "level_screen")
    object ProfileScreen : Screen(route="profile_screen")
    object EditScreen : Screen(route="edit_screen")
}
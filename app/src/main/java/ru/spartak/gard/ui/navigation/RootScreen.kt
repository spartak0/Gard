package ru.spartak.gard.ui.navigation

sealed class RootScreen(val route: String) {
    object LevelUpScreen : Screen(route="level_up_screen")
    object Confirmation : Screen(route="confirmation_screen")
}
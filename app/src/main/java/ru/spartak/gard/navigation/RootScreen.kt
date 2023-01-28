package ru.spartak.gard.navigation

sealed class RootScreen(val route: String) {
    object LevelUpScreen : Screen(route="level_up_screen")
    object Confirmation : Screen(route="confirmation_screen")
    object Login : Screen(route="login_screen")
    object Splash : Screen(route="splash_screen")
}
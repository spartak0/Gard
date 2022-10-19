package ru.spartak.gard.ui.navigation

sealed class Screen(val route: String) {
    object LevelScreen : Screen(route = "level_screen")
    object ProfileScreen : Screen(route="profile_screen")
    object EditScreen : Screen(route="edit_screen")
    object SettingsScreen : Screen(route="settings_screen")
    object NotificationsScreen : Screen(route="notifications_screen")
    object LevelUpScreen : Screen(route="level_up_screen") //root
    object Confirm : Screen(route="confirm_screen") //root
}
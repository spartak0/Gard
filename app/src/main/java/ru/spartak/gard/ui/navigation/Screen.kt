package ru.spartak.gard.ui.navigation

sealed class Screen(val route: String) {
    //home
    object LevelScreen : Screen(route = "level_screen")
    object ProfileScreen : Screen(route="profile_screen")
    object EditScreen : Screen(route="edit_screen")
    object SettingsScreen : Screen(route="settings_screen")
    object NotificationsScreen : Screen(route="notifications_screen")

    //games
    object DetailScreen:Screen(route="detail_screen")
    object SeasonsScreen:Screen(route="seasons_screen")
    object StatsDisconnectedGameScreen:Screen(route="stats_disconnected_game_screen")
    object ConnectGame:Screen(route="connect_game_screen")

    //tasks
    object CompletedTasks:Screen(route = "completed_tasks_screen")
}
package ru.spartak.gard.ui.navigation.content

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.navigation.RootScreen
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.EditScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.levels_screen.LevelsScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.notifications_screen.NotificationsScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.ProfileScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.settings_screen.SettingsScreen
import ru.spartak.gard.ui.root_screen.main_screen.tasks_tab.completed_tasks_screen.CompletedTasksScreen
import ru.spartak.gard.utils.Constant

fun NavGraphBuilder.tasksContentComposable(
    rootNavController: NavController,
    mainNavController: NavController
) {
    composable(route = Screen.CompletedTasks.route) {
        CompletedTasksScreen(navController = mainNavController)
    }
}
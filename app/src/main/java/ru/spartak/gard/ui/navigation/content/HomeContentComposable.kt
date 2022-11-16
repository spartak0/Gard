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

fun NavGraphBuilder.homeContentComposable(
    rootNavController: NavController,
    mainNavController: NavController
) {
    composable(route = Screen.LevelScreen.route) {
        LevelsScreen(navController = mainNavController)
    }
    composable(route = Screen.ProfileScreen.route) {
        ProfileScreen(
            rootNavController=rootNavController,
            mainNavController = mainNavController,
            levelOnClick = { rootNavController.navigate(RootScreen.LevelUpScreen.route) }
        )
    }
    composable(route = Screen.EditScreen.route) {
        EditScreen(navController = mainNavController, rootNavController = rootNavController)
    }
    composable(route = Screen.SettingsScreen.route) {
        SettingsScreen(navController = mainNavController)
    }
    composable(route = Screen.NotificationsScreen.route) {
        NotificationsScreen(navController = mainNavController)
    }
}
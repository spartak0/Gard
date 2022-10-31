package ru.spartak.gard.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.EditScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.levels_screen.LevelsScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.notifications_screen.NotificationsScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.ProfileScreen
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.settings_screen.SettingsScreen
import ru.spartak.gard.utils.Constant

fun NavGraphBuilder.homeContentComposable(rootNavController:NavController, mainNavController:NavController) {
    composable(route = Screen.LevelScreen.route) {
        LevelsScreen(navController = mainNavController)
    }
    composable(route = Screen.ProfileScreen.route) {
        mainNavController.previousBackStackEntry?.arguments?.getBoolean(Constant.SAVE_TOAST_KEY).let {
            ProfileScreen(
                navController = mainNavController,
                showSaveToast = it ?: false,
                levelOnClick = { rootNavController.navigate(RootScreen.LevelUpScreen.route) }
            )
        }
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
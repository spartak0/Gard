package ru.spartak.gard.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.edit_screen.EditScreen
import ru.spartak.gard.ui.home_screen.HomeScreen
import ru.spartak.gard.ui.levels_screen.LevelsScreen
import ru.spartak.gard.ui.notifications_screen.NotificationsScreen
import ru.spartak.gard.ui.profile_screen.ProfileScreen
import ru.spartak.gard.ui.settings_screen.SettingsScreen
import ru.spartak.gard.utils.Constant

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String,
    rootNavController: NavController,
    modifier: Modifier=Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = BottomScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomScreen.GamesScreen.route) {
        }
        composable(route = BottomScreen.TasksScreen.route) {
        }
        composable(route = BottomScreen.ShopScreen.route) {
        }

        composable(route = Screen.LevelScreen.route) {
            LevelsScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            navController.previousBackStackEntry?.arguments?.getBoolean(Constant.SAVE_TOAST_KEY).let {
                ProfileScreen(
                    navController = navController,
                    showSaveToast = it ?: false,
                    levelOnClick = {rootNavController.navigate(RootScreen.LevelUpScreen.route)}
                )
            }
        }
        composable(route = Screen.EditScreen.route) {
            EditScreen(navController = navController, rootNavController = rootNavController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.NotificationsScreen.route) {
            NotificationsScreen(navController = navController)
        }
    }
}


fun NavController.navigate(
    route: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}

fun NavController.navigateUp(
    params: Bundle?,
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    val a =this.currentBackStackEntry?.destination?.route
    if (a != null) {
        popBackStack(route = a, inclusive = true)
    }
}
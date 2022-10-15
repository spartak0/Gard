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
import ru.spartak.gard.ui.profile_screen.ProfileScreen
import ru.spartak.gard.ui.settings_screen.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
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
            navController.previousBackStackEntry?.arguments?.getBoolean("123").let {
                ProfileScreen(navController = navController, showSaveToast = it?:false)
            }
        }
        composable(route = Screen.EditScreen.route) {
            EditScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}


fun NavController.navigate(route: String, params: Bundle?, builder: NavOptionsBuilder.() -> Unit = {}) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}
package ru.spartak.gard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.home_screen.HomeScreen
import ru.spartak.gard.ui.levels_screen.LevelsScreen
import ru.spartak.gard.ui.profile_screen.ProfileScreen

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
            ProfileScreen(navController = navController)
        }
    }
}
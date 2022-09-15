package ru.spartak.gard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController, startDestination:String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.HomeScreen.route) {
        }
        composable(route = Screen.GamesScreen.route) {
        }
        composable(route = Screen.TasksScreen.route) {
        }
        composable(route= Screen.ShopScreen.route){
        }
    }
}
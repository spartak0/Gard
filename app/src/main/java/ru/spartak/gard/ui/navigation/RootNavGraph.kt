package ru.spartak.gard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.root_screen.confirmation_screen.ConfirmationScreen
import ru.spartak.gard.ui.root_screen.level_up_screen.LevelUpScreen
import ru.spartak.gard.ui.root_screen.main_screen.MainScreen
import ru.spartak.gard.utils.Constant

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graphs.Root,
        modifier = modifier
    ) {
        composable(route = Graphs.Main) {
            navController.previousBackStackEntry?.arguments.let {
                val mainStartDestination = it?.getString(Constant.MAIN_GRAPH_START_DESTINATION)
                    ?: BottomScreen.HomeScreen.route
                MainScreen(
                    rootNavController = navController,
                    startDestination = mainStartDestination
                )
            }
        }
        composable(route = RootScreen.LevelUpScreen.route) {
            LevelUpScreen(
                navController = navController,
            )
        }
        composable(route = RootScreen.Confirmation.route) {
            ConfirmationScreen(
                navController = navController
            )
        }
    }
}
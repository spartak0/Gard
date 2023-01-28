package ru.spartak.gard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.root_screen.confirmation_screen.ConfirmationScreen
import ru.spartak.gard.ui.root_screen.level_up_screen.LevelUpScreen
import ru.spartak.gard.ui.root_screen.login_screen.LoginScreen
import ru.spartak.gard.ui.root_screen.main_screen.MainScreen
import ru.spartak.gard.ui.root_screen.splash_screen.SplashScreen
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
            navController.previousBackStackEntry?.let {
                val confirmationBundle = it.arguments?.getBundle(Constant.CONFIRMATION_BUNDLE) ?: bundleOf(
                    Constant.MAIN_GRAPH_START_DESTINATION to BottomScreen.HomeScreen.route
                )
                ConfirmationScreen(
                    navController = navController,
                    bundleNavigation = confirmationBundle,
                    viewModel = hiltViewModel(it)
                )
            }
        }
        composable(route = RootScreen.Login.route) {
            navController.currentBackStackEntry?.let {
                LoginScreen(
                    navController = navController,
                    viewModel = hiltViewModel(it)
                )
            }

        }
        composable(route = RootScreen.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
    }
}
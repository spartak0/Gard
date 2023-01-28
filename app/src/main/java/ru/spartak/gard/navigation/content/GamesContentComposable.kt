package ru.spartak.gard.navigation.content

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.spartak.gard.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.connect_game_screen.ConnectGameScreen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsScreen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsViewModel
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.StatsDisconnectedGame
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.season_screen.SeasonScreen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.utils.Constant

fun NavGraphBuilder.gamesContentComposable(
    rootNavController: NavController,
    mainNavController: NavController,
) {

    composable(route = Screen.DetailScreen.route) {
        mainNavController.currentBackStackEntry?.let { backStackEntry ->
            val viewModel = hiltViewModel<DetailsViewModel>(backStackEntry)
            mainNavController.previousBackStackEntry?.arguments.let {
                val connectionStatus = it?.getSerializable(Constant.GAME_STATUS_KEY)?: ConnectionStatus.DISCONNECTED
                val startViewPagerTab = it?.getInt(Constant.START_VIEW_PAGER_TAB)?:0
                val errorToastState = it?.getBoolean(Constant.ERROR_TOAST_KEY)?:false
                DetailsScreen(
                    navController = mainNavController,
                    connectionStatus = connectionStatus as ConnectionStatus,
                    startViewPagerTab= startViewPagerTab,
                    errorToastState = errorToastState,
                    viewModel = viewModel,
                )
            }
        }
    }

    composable(route = Screen.SeasonsScreen.route) {
        mainNavController.previousBackStackEntry?.let { backStackEntry ->
            val viewModel = hiltViewModel<DetailsViewModel>(backStackEntry)
            SeasonScreen(navController = mainNavController, viewModel)
        }
    }

    composable(route = Screen.StatsDisconnectedGameScreen.route) {
        StatsDisconnectedGame(navController = mainNavController)
    }

    composable(route = Screen.ConnectGame.route) {
        ConnectGameScreen(navController = mainNavController)
    }

}

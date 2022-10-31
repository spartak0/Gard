package ru.spartak.gard.ui.navigation.content

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsScreen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.utils.Constant

fun NavGraphBuilder.gamesContentComposable(
    rootNavController: NavController,
    mainNavController: NavController
) {
    composable(route = Screen.DetailScreen.route) {
        mainNavController.previousBackStackEntry?.arguments?.getSerializable(Constant.GAME_STATUS_KEY)
            .let {
                DetailsScreen(
                    navController = mainNavController,
                    (it ?: ConnectionStatus.DISCONNECTED) as ConnectionStatus
                )
            }
    }
}
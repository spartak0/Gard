package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsViewModel
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus

@Composable
fun StatsTab(
    connectionStatus: ConnectionStatus,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    when (connectionStatus) {
        ConnectionStatus.CONNECTED -> StatsConnectedGame(
            navController = navController,
            viewModel = viewModel
        )
        ConnectionStatus.DISCONNECTED -> {
            //not need handle, watch details screen
        }
        ConnectionStatus.SOON -> {}
    }
}
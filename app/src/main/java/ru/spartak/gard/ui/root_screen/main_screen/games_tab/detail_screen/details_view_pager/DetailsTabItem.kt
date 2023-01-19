package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsViewModel
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.information_tab.InformationTab
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.StatsTab
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus

sealed class DetailsTabItem(
    val titleId: Int,
    val screen: @Composable (ConnectionStatus, NavController, DetailsViewModel) -> Unit
) {

    object Information :
        DetailsTabItem(
            titleId = R.string.information,
            screen = { gameStatus, navController, _ -> InformationTab(gameStatus, navController) })

    object Stats :
        DetailsTabItem(
            titleId = R.string.my_stats_2,
            screen = { gameStatus, navController, viewModel ->
                StatsTab(
                    gameStatus,
                    navController,
                    viewModel
                )
            })
}
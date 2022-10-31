package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen

import androidx.compose.runtime.Composable
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus

sealed class TabItem(val title: String, val screen: @Composable (ConnectionStatus) -> Unit) {
    object Information : TabItem(title = "Information", screen = {gameStatus-> InformationScreen(gameStatus) })
    object Stats : TabItem(title = "My stats", screen = {gameStatus-> StatsScreen(gameStatus) })
}

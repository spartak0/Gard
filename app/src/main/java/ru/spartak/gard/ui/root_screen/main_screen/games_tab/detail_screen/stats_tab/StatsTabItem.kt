package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab

import androidx.compose.runtime.Composable
import ru.spartak.gard.ui.details.TabItem
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.DuosFragment
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.SoloFragment
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.SquadsFragment

sealed class StatsTabItem(override val title: String, override val content: @Composable () -> Unit) :
    TabItem {
    object Solo : StatsTabItem(title = "Solo", content = { SoloFragment() })
    object Duos : StatsTabItem(title = "Duos", content = { DuosFragment() })
    object Squads : StatsTabItem(title = "Squads", content = { SquadsFragment() })
}
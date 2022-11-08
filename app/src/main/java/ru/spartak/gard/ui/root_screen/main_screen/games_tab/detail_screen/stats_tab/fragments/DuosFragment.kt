package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DuosFragment() {
    val statList = listOf(
        Pair("Score", "8998210"),
        Pair("Matches", "28888"),
        Pair("Duo", "3888"),
        Pair("Top 3/5/102", "23"),
        Pair("Top 3/5/104", "23"),
        Pair("Time played", "1000D 23H 14M"),
    )
    StatGrid(statList = statList)
}
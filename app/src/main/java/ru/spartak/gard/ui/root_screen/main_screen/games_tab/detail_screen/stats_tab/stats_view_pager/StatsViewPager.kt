package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.stats_view_pager

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.DuosFragment
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.SoloFragment
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.fragments.SquadsFragment
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.Text500

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StatsViewPagerTabs(tabs: List<StatsTabItem>, pagerState: PagerState, modifier: Modifier) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Text50,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index
            Tab(
                text = {
                    Text(
                        tab.title,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                        color = if (isSelected) Text50 else Text500
                    )
                },
                selected = isSelected,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StatsTabsContent(tabs: List<StatsTabItem>, pagerState: PagerState, modifier: Modifier) {
    HorizontalPager(
        state = pagerState,
        count = tabs.size,
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) { page ->
        tabs[page].content()
    }
}

sealed class StatsTabItem(val title: String, val content: @Composable () -> Unit) {
    object Solo : StatsTabItem(title = "Solo", content = { SoloFragment() })
    object Duos : StatsTabItem(title = "Duos", content = { DuosFragment() })
    object Squads : StatsTabItem(title = "Squads", content = { SquadsFragment() })
}
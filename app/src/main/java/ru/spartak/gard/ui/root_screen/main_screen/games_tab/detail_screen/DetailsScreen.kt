package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.spacing

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(navController: NavController, connectionStatus: ConnectionStatus) {
    val tabs = listOf(
        TabItem.Information,
        TabItem.Stats,
    )
    val pagerState = rememberPagerState()
    GardTheme {
        Column {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DetailsTopBar(subtitle =tabs[pagerState.currentPage].title) {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, connectionStatus = connectionStatus)
        }

    }
}

@Composable
fun DetailsTopBar(subtitle: String, backOnClick: () -> Unit) {
    TopBar(
        subtitleText = subtitle,
        leftView = {
            BackBtn { backOnClick() }
        }, modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(42.dp),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Text50,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, connectionStatus: ConnectionStatus) {
    HorizontalPager(state = pagerState, count = tabs.size, modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top) { page ->
        tabs[page].screen(connectionStatus)
    }
}

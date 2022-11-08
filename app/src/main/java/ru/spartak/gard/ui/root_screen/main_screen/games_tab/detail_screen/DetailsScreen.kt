package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsTabItem
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsTabsContent
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsViewPagerTabs
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(navController: NavController, connectionStatus: ConnectionStatus) {
    val tabs = listOf(
        DetailsTabItem.Information,
        DetailsTabItem.Stats,
    )
    val pagerState = rememberPagerState()
    GardTheme {
        Column {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DetailsTopBar(subtitle = tabs[pagerState.currentPage].title) {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DetailsViewPagerTabs(
                tabs = tabs,
                pagerState = pagerState,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(42.dp)
            )
            DetailsTabsContent(
                tabs = tabs,
                pagerState = pagerState,
                connectionStatus = connectionStatus,
                modifier = Modifier.fillMaxSize()
            )
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

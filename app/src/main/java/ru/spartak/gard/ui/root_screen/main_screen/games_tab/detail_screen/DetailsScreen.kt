package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsTabItem
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsTabsContent
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.details_view_pager.DetailsViewPagerTabs
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.Constant

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    connectionStatus: ConnectionStatus,
    startViewPagerTab:Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val tabs = listOf(
        DetailsTabItem.Information,
        DetailsTabItem.Stats,
    )
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        this.launch(Dispatchers.IO) {
            pagerState.scrollToPage(startViewPagerTab)
        }
    }
    GardTheme {
        Column {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
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
            ) { index, tab ->
                scope.launch {
                    if (connectionStatus == ConnectionStatus.CONNECTED)
                        pagerState.animateScrollToPage(index)
                    else if (tab == DetailsTabItem.Stats) navController.navigate(Screen.StatsDisconnectedGameScreen.route)
                }
            }
            DetailsTabsContent(
                tabs = tabs,
                pagerState = pagerState,
                connectionStatus = connectionStatus,
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    //clear(navController=navController)
}

fun clear(navController: NavController){
    navController.previousBackStackEntry?.arguments?.clear()
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

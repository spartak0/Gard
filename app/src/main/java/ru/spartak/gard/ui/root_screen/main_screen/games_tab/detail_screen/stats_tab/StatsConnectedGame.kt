package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BtnGroup
import ru.spartak.gard.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsViewModel
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.models.Platform
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.models.StatTime
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.stats_view_pager.StatsTabItem
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.stats_view_pager.StatsTabsContent
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.stats_view_pager.StatsViewPagerTabs
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.ParserDecimal

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StatsConnectedGame(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedSeason = viewModel.selectedSeason.collectAsState()
    val selectedPlatform = remember { mutableStateOf(Platform.All as Platform) }
    val selectedTime = remember { mutableStateOf(StatTime.SevenDays as StatTime) }
    val platformList = listOf(Platform.All, Platform.PC, Platform.Console, Platform.Mobile)
    val listTime = listOf(StatTime.SevenDays, StatTime.ThirtyDays, StatTime.Lifetime)
    val tabs = listOf(StatsTabItem.Solo, StatsTabItem.Duos, StatsTabItem.Squads)
    val pagerState = rememberPagerState()

    GardTheme {
        Column(
            Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            StatsNickname("NAGIBATOR228")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            EpicIdText("9ffa90009d")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitlePlatform()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            PlatformGroup(
                selected = selectedPlatform,
                list = platformList,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitleSeason()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Season(
                selectedSeason = selectedSeason.value,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = { navController.navigate(Screen.SeasonsScreen.route) })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            val statList = listOf(
                Pair("Score", "8998210"),
                Pair("Matches", "28888"),
                Pair("Matches2", "3888"),
                Pair("Top 3/5/10", "23"),
                Pair("Top 3/5/102", "23"),
                Pair("Top 3/5/103", "23"),
                Pair("Top 3/5/104", "23"),
                Pair("Time played", "1000D 23H 14M"),
            )
            OverallStatsCard(
                statList = statList,
                timeList = listTime,
                selectedTime = selectedTime,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(28.dp))
            StatsViewPagerTabs(
                tabs = tabs,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            StatsTabsContent(
                tabs = tabs,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        }
    }
}

@Composable
fun OverallStatsCard(
    statList: List<Pair<String, String>>,
    timeList: List<StatTime>,
    selectedTime: MutableState<StatTime>,
    modifier: Modifier
) {

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.mediumLarge
                )
        ) {
            Text(
                text = "Overall",
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                color = Text500
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            BtnGroup(
                selected = selectedTime,
                list = timeList,
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) { time ->
                Text(
                    text = time.text,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            FlowRow(
                crossAxisSpacing = MaterialTheme.spacing.medium,
                mainAxisSpacing = MaterialTheme.spacing.smallMedium,
            ) {
                for (index in (0..statList.size - 2)) {
                    val height = if (index == 0) 68.dp else 58.dp
                    val width = (LocalConfiguration.current.screenWidthDp.dp - 64.dp - 12.dp) / 2
                    Column(
                        modifier = Modifier
                            .height(height)
                            .width(width)
                    ) {
                        Text(
                            text = statList[index].first,
                            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
                            color = Text400
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                        Text(
                            text = ParserDecimal.pars(statList[index].second),
                            style = if (index != 0) MaterialTheme.typography.body1 else MaterialTheme.typography.h5
                        )
                    }
                }

            }
            Divider(modifier = Modifier.fillMaxWidth(), color = Dark200, thickness = 1.dp)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = statList.last().first,
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
                color = Text400
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Text(
                text = statList.last().second,
                style = MaterialTheme.typography.body1
            )

        }
    }
}

@Composable
fun Season(selectedSeason: String, modifier: Modifier, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(
                RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = selectedSeason,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun SubtitleSeason() {
    Text(
        text = "Season",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
    )
}

@Composable
fun SubtitlePlatform() {
    Text(
        text = "Platform",
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
        color = Text500
    )
}

@Composable
fun EpicIdText(id: String) {
    Text(
        text = "Epic ID: $id",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
        color = Text500
    )
}

@Composable
fun StatsNickname(name: String) {
    Text(text = name, style = MaterialTheme.typography.h6)

}

@SuppressLint("UnrememberedAnimatable")
@Composable
fun PlatformGroup(selected: MutableState<Platform>, list: List<Platform>, modifier: Modifier) {
    BtnGroup(selected = selected, list = list, modifier = modifier) { platform ->
        if (platform.iconId != null) Icon(
            painter = painterResource(id = platform.iconId),
            contentDescription = null,
            tint = White,
            modifier = Modifier.size(20.dp)
        ) else
            Text(
                text = platform.name,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
            )
    }

}

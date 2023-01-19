package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.season_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.root_screen.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.DetailsViewModel
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SeasonScreen(navController: NavController, viewModel: DetailsViewModel = hiltViewModel()) {
    val selectedSeason =  viewModel.selectedSeason.collectAsState()
    GardTheme {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SeasonTopBar(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            viewModel.seasonList.forEach { season ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable {
                            viewModel.changeSelectedSeason(season)
                            navController.navigateUp()
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = season,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
                    )
                    AnimatedVisibility(visible = selectedSeason.value == season) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check_mark),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .padding(end = MaterialTheme.spacing.smallMedium)
                                .size(20.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun SeasonTopBar(backOnClick: () -> Boolean) {
    TopBar(
        subtitleText = stringResource(R.string.season),
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}

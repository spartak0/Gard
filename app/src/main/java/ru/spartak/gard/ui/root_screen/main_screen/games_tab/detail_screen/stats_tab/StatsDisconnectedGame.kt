package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.root_screen.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.information_tab.DisconnectedActionBtn
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@Composable
fun StatsDisconnectedGame(navController: NavController) {
    val gameName = "Fortnite"

    GardTheme {
        Column() {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            DisconnectedStatsTopBar { navController.navigateUp() }
            Spacer(modifier = Modifier.height(114.dp))
            Image(
                painter = painterResource(id = R.drawable.disconnected_game),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(
                text = "Oops!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = "Your stats list is empty. That’s because your haven’t got Fortnite connected yet. Let’s handle this!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.smallMedium)
                    .fillMaxWidth()
                    .height(38.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            DisconnectedActionBtn(
                text = "Connect $gameName",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp)
            ) {
                navController.navigate(Screen.ConnectGame.route)
            }

        }

    }
}

@Composable
fun DisconnectedStatsTopBar(backOnClick: () -> Boolean) {
    TopBar(
        subtitleText = stringResource(R.string.my_stats),
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .padding(horizontal = MaterialTheme.spacing.medium),
        leftView = { BackBtn { backOnClick() } },
    )
}
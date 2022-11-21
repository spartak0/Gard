package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.information_tab

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.Dialog
import ru.spartak.gard.ui.details.bottomAlign
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.StatusGameView
import ru.spartak.gard.ui.theme.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InformationTab(connectionStatus: ConnectionStatus, navController: NavController) {
    val tmp = listOf(
        painterResource(id = R.drawable.fortnite_image_tmp),
        painterResource(id = R.drawable.fortnite_image_tmp),
        painterResource(id = R.drawable.fortnite_image_tmp)
    )
    val pagerState = rememberPagerState()
    val showDialog = remember { mutableStateOf(false) }

    GardTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
                ImageViewPagerContent(
                    image = tmp, pagerState = pagerState, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        GameName(name = "Fortnite")
                        GameCompany(name = "EpicGames")

                    }
                    StatusGameView(connectionStatus = connectionStatus)
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                Row(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
                    GameOnline("328K online")
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
                    MonthTasks("40+ tasks / month")
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                GameDescription(
                    "Fortnite is a survival game where 100 players fight against each other in player versus player combat to be the last one standing. It is a fast-paced, action-packed game, not unlike The Hunger Games, where strategic thinking is a must in order to survive. There are an estimated 125 million players on Fortnite.",
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                StructureView(
                    text = listOf(
                        "30 daily tasks",
                        "4 weekly tasks",
                        "1 monthly task"
                    ),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                )
                if (connectionStatus == ConnectionStatus.CONNECTED) {
                    Spacer(modifier = Modifier.height(48.dp))
                    DisconnectTextBtn(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.medium)
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(41.dp)
                    ) {
                        showDialog.value = true
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(Dark100)
                    .fillMaxWidth()
            ) {
                LowerActionBtn(
                    status = connectionStatus, modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                        .height(40.dp),
                    navController = navController
                )
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Dark200)
            }
            if (showDialog.value) {
                DisconnectGameDialog(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(bottom = 40.dp)
                        .bottomAlign()
                        .fillMaxWidth(), showDialog = showDialog
                )
            }
        }
    }
}

@Composable
fun DisconnectGameDialog(modifier: Modifier, showDialog: MutableState<Boolean>) {
    Dialog(
        subtitle = "Are you sure you want disconnect Fortnite?",
        text = "Your statistics will be reseted as well.",
        confirmText = "Disconnect",
        rejectText = "Cancel",
        showDialog = showDialog,
        modifier = modifier
    ) {

    }
}

@Composable
fun DisconnectTextBtn(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.clickable { onClick() }, contentAlignment = Alignment.Center) {
        Text(
            text = "Disconnect from my games",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
            color = Error500,
        )
    }
}

@Composable
fun LowerActionBtn(status: ConnectionStatus, modifier: Modifier, navController: NavController) {
    when (status) {
        ConnectionStatus.CONNECTED -> ConnectedActionBtn(modifier = modifier) {
        }
        ConnectionStatus.DISCONNECTED -> DisconnectedActionBtn(
            text = "Connect",
            modifier = modifier
        ) {
            navController.navigate(
                Screen.ConnectGame.route
            )
        }
        ConnectionStatus.SOON -> {}
    }

}

@Composable
fun ConnectedActionBtn(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "To the tasks",
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = Text50,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun DisconnectedActionBtn(text: String, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_connect),
                contentDescription = null,
                tint = Text50,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Text(
                text = text,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}


@Composable
fun StructureView(text: List<String>, modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.secondary)
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            Text(text = "Structure", style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            text.forEach {
                Text(text = "• $it", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun GameDescription(text: String, modifier: Modifier) {
    Text(text = text, style = MaterialTheme.typography.body2, modifier = modifier)
}

@Composable
fun GameOnline(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_gamers),
            contentDescription = null,
            tint = Text50,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = text,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun MonthTasks(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_ckeck_circle),
            contentDescription = null,
            tint = Text50,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = text,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun GameName(name: String) {
    Text(text = name, style = MaterialTheme.typography.h6)
}

@Composable
fun GameCompany(name: String) {
    Text(text = name, style = MaterialTheme.typography.caption, color = Text500)
}

@ExperimentalPagerApi
@Composable
fun ImageViewPagerContent(image: List<Painter>, pagerState: PagerState, modifier: Modifier) {
    HorizontalPager(state = pagerState, count = image.size, modifier = modifier) { index ->
        Image(painter = image[index], contentDescription = null, contentScale = ContentScale.Crop)
    }
}

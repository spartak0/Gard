package ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.Border
import ru.spartak.gard.ui.details.CustomTextField
import ru.spartak.gard.ui.details.LoadBtn
import ru.spartak.gard.ui.details.border
import ru.spartak.gard.navigation.Screen
import ru.spartak.gard.navigation.navigate
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant

@Composable
fun GamesScreen(navController: NavController) {
    val suggestGameVisible = remember { mutableStateOf(true) }
    val suggestGameText = remember { mutableStateOf("") }
    val loadStateBtn = remember { mutableStateOf(false) }

    GardTheme {
        Column(
            Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            SubtitleSupportedGames()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            GameCard(
                name = "Fortnite",
                company = "Epic Games",
                imageId = R.drawable.fortnite_image_tmp,
                connectionStatus = ConnectionStatus.CONNECTED,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(332.dp),
                onClickContainer = {
                    navController.navigate(
                        Screen.DetailScreen.route,
                        bundleOf(
                            Constant.GAME_STATUS_KEY to ConnectionStatus.CONNECTED,
                            Constant.START_VIEW_PAGER_TAB to 0
                        )
                    )
                },
                onClickBtn = {
                    navController.navigate(
                        Screen.DetailScreen.route,
                        bundleOf(
                            Constant.GAME_STATUS_KEY to ConnectionStatus.CONNECTED,
                            Constant.START_VIEW_PAGER_TAB to 1
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            GameCard(
                name = "Apex",
                company = "Respawn Entertainment",
                imageId = R.drawable.apex_background,
                connectionStatus = ConnectionStatus.DISCONNECTED,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(332.dp),
                onClickContainer = {
                    navController.navigate(
                        Screen.DetailScreen.route,
                        bundleOf(
                            Constant.GAME_STATUS_KEY to ConnectionStatus.DISCONNECTED,
                            Constant.START_VIEW_PAGER_TAB to 0
                        )
                    )
                },
                onClickBtn = {
                    navController.navigate(Screen.ConnectGame.route)
                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            GameCard(
                name = "Dota 2",
                company = "Valve",
                imageId = R.drawable.fortnite_image_tmp,
                connectionStatus = ConnectionStatus.SOON,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(332.dp),
                onClickContainer = {
                },
                onClickBtn = { }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            AnimatedVisibility(visible = suggestGameVisible.value) {
                SuggestGameView(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.mediumLarge),
                    close = { suggestGameVisible.value = false })
            }
            SuggestGameTextField(
                text = suggestGameText, modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            ProceedBtn(modifier = Modifier
                .fillMaxWidth()
                .height(41.dp),
                loadState = loadStateBtn.value,
                onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    loadStateBtn.value = true
                    val tmp = CoroutineScope(Dispatchers.IO).launch {
                        delay(2000)
                    }
                    tmp.join()
                    loadStateBtn.value = false
                }
            })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        }
    }
}

@Composable
fun ProceedBtn(modifier: Modifier,loadState: Boolean, onClick: () -> Unit) {
    LoadBtn(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp)),
        text= "Proceed",
        loadState = loadState
    ) {
        onClick()
    }
}

@Composable
fun SuggestGameTextField(text: MutableState<String>, modifier: Modifier) {
    CustomTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )

}

@Composable
fun SuggestGameView(modifier: Modifier, close: () -> Unit) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .border(start = Border(4.dp, Tertiary500))
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.medium
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "There’s no your game in this list?",
                modifier = Modifier.width(240.dp),
                style = MaterialTheme.typography.h5
            )
            Box(
                Modifier
                    .clip(CircleShape)
                    .clickable { close() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    tint = Dark500,
                    modifier = Modifier.padding(MaterialTheme.spacing.smallMedium)
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = "Type your game below, and we try our best to add it to our supported list!",
            modifier = Modifier.width(240.dp),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
        )


    }
}

@Composable
fun SubtitleSupportedGames() {
    Text(text = "Supported games", style = MaterialTheme.typography.subtitle1)
}

@Composable
fun GameCard(
    name: String,
    company: String,
    imageId: Int,
    connectionStatus: ConnectionStatus,
    modifier: Modifier,
    onClickContainer: () -> Unit,
    onClickBtn: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Dark100)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .clickable { onClickContainer() }) {
            ImageGameCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painterResource(id = imageId), connectionStatus
            )
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.mediumLarge
                    )
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = name, style = MaterialTheme.typography.h6)
                    Text(
                        text = company,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal)
                    )
                }
                StatusGameView(connectionStatus)
            }
        }
        BtmBtnStatusGameView(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp),
            connectionStatus
        ) { onClickBtn() }


    }
}

@Composable
fun ImageGameCard(
    modifier: Modifier,
    painterResource: Painter,
    connectionStatus: ConnectionStatus
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (connectionStatus == ConnectionStatus.SOON)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF17171A).copy(alpha = 0.8f))
            )
    }
}

@Composable
fun BtmBtnStatusGameView(
    modifier: Modifier,
    connectionStatus: ConnectionStatus,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(MaterialTheme.colors.primary)
            .clickable { onClick() }
    ) {
        when (connectionStatus) {
            ConnectionStatus.CONNECTED -> {
                Text(text = "My stats", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    tint = Text50,
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
            }
            ConnectionStatus.DISCONNECTED -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_connect),
                    tint = Text50,
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Text(text = "Connect to my account", style = MaterialTheme.typography.body2)
            }
            ConnectionStatus.SOON -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_bell),
                    tint = Text50,
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Text(text = "Notify me", style = MaterialTheme.typography.body2)
            }
        }

    }
}

@Composable
fun StatusGameView(connectionStatus: ConnectionStatus) {
    when (connectionStatus) {
        ConnectionStatus.CONNECTED -> StatusGameConnectedView()
        ConnectionStatus.DISCONNECTED -> StatusGameDisconnectedView()
        ConnectionStatus.SOON -> StatusGameSoonView()
    }
}

@Composable
fun StatusGameSoonView() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .height(20.dp)
            .background(
                Tertiary500
            )
    ) {
        Text(
            text = "Soon",
            style = MaterialTheme.typography.caption,
            color = Text900,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}


@Composable
fun StatusGameConnectedView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .height(20.dp)
            .background(
                Success600
            ),
    ) {
        Text(
            text = "Connected",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}

@Composable
fun StatusGameDisconnectedView() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .height(20.dp)
            .background(
                Dark300
            )
    ) {
        Text(
            text = "Not connected",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}

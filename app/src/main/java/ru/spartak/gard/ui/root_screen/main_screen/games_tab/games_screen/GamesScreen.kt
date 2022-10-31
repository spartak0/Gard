package ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import ru.spartak.gard.R
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.navigation.navigate
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant

@Composable
fun GamesScreen(navController: NavController) {
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
                onClick = {navController.navigate(Screen.DetailScreen.route, bundleOf(Constant.GAME_STATUS_KEY to ConnectionStatus.CONNECTED))}
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            GameCard(
                name = "Fortnite",
                company = "Epic Games",
                imageId = R.drawable.fortnite_image_tmp,
                connectionStatus = ConnectionStatus.DISCONNECTED,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(332.dp),
                onClick = {navController.navigate(Screen.DetailScreen.route, bundleOf(Constant.GAME_STATUS_KEY to ConnectionStatus.DISCONNECTED))}
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            GameCard(
                name = "Fortnite",
                company = "Epic Games",
                imageId = R.drawable.fortnite_image_tmp,
                connectionStatus = ConnectionStatus.SOON,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(332.dp),
                onClick = {navController.navigate(Screen.DetailScreen.route, bundleOf(Constant.GAME_STATUS_KEY to ConnectionStatus.SOON))}
            )
        }
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
    onClick:()->Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Dark100)
            .clickable { onClick() }
    ) {
        ImageGameCard(painterResource(id = imageId), connectionStatus)
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
        BtmBtnStatusGameView(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp), connectionStatus
        )


    }
}

@Composable
fun ImageGameCard(painterResource: Painter, connectionStatus: ConnectionStatus) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
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
fun BtmBtnStatusGameView(modifier: Modifier, connectionStatus: ConnectionStatus) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.background(MaterialTheme.colors.primary)
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

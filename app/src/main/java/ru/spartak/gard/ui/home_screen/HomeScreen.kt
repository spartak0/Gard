package ru.spartak.gard.ui.home_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.*


//todo add notification service
@Composable
fun HomeScreen(navController: NavController) {
    GardTheme() {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Text(text = stringResource(R.string.welcome), style = MaterialTheme.typography.subtitle2, color = TrueGray500)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Username(username = "nagibat8".uppercase())
                NotificationBell(countNotification = 1)
            }
            Spacer(modifier = Modifier.height(21.dp))
            ProfileCard(onClick = {navController.navigate(Screen.LevelScreen.route)})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(text = stringResource(R.string.balance), style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Row(modifier = Modifier.fillMaxWidth()) {
                val ptsCardHeight = 90.dp
                TotalPts(
                    modifier = Modifier
                        .height(ptsCardHeight)
                        .weight(0.61f)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                WeekPts(
                    Modifier
                        .height(ptsCardHeight)
                        .weight(0.39f)
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(text = stringResource(R.string.tasks), style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            (0..2).forEach { _ ->
                TasksCard()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }
            CardConnectFirstGame()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(text = stringResource(R.string.earnings), style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Text(text = stringResource(R.string.today), style = MaterialTheme.typography.body2, color = Text500)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            (0..2).forEach {
                EarningsCard()
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.yesterday)+" 03 May 2022",
                style = MaterialTheme.typography.body2,
                color = Text500
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            (0..2).forEach {
                EarningsCard()
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(modifier = Modifier.height(52.dp))
        }
    }
}

@Composable
fun EarningsCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        color = Color.Transparent
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.fortnite_logo_natification_false),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    Text(
                        text = "MultiKill 30",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = "Fortnite daily reward",
                        style = MaterialTheme.typography.caption,
                        color = Text500
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "+12", style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Image(
                    painter = painterResource(id = R.drawable.ic_g_pts),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun CardConnectFirstGame() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        border = BorderStroke(2.dp, Tertiary500),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.not_games_connect), style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(
                    text = stringResource(id = R.string.add_first_game),
                    style = MaterialTheme.typography.overline.copy(letterSpacing = 0.15.sp)
                )
            }
            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .width(125.dp),
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.to_the_games),
                            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
                            color = White,
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = null,
                            tint = White
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun TasksCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.newbie_image),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Text(
                    text = "Fortnite tasks",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = Muted200
                )
            }
            Column {
                Surface(
                    shape = RoundedCornerShape(2.dp),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.height(20.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "2 active",
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Surface(
                    shape = RoundedCornerShape(2.dp),
                    color = Success500,
                    modifier = Modifier.height(20.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "1 completed",
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                        )
                    }

                }

            }
        }


    }
}

@Composable
fun TotalPts(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.total),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Text500
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "48 885", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Image(
                    painter = painterResource(id = R.drawable.ic_g_pts),
                    alignment = Alignment.Center,
                    contentDescription = null
                )
            }

        }

    }
}

@Composable
fun WeekPts(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Text(text = stringResource(R.string.this_week), style = MaterialTheme.typography.body2, color = Text500)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "+120", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Image(
                    painter = painterResource(id = R.drawable.ic_g_pts),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ProfileCard(onClick:()->Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colors.secondary, shape = RoundedCornerShape(4.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.newbie_image),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.newbie), style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = Muted200
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "0%", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    Image(
                        painter = painterResource(id = R.drawable.ic_g_pts),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    Text(
                        text = stringResource(R.string.bonus),
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
                        color = Tertiary500
                    )

                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0", style = MaterialTheme.typography.caption, color = Text500)
                Text(text = "5 000 " + stringResource(R.string.pts), style = MaterialTheme.typography.caption, color = Text500)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .height(4.dp),
                backgroundColor = Muted500,
                progress = 0.7f,
                color = Tertiary500
            )
        }
    }


}


@Composable
fun Username(username: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = username, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
            tint = Muted200
        )
    }
}

@Composable
fun NotificationBell(countNotification: Int) {
    BadgedBox(badge = {
        if (countNotification > 0) {
            Badge(
                modifier = Modifier.absoluteOffset((-10).dp, (5).dp),
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Text(
                    text = "$countNotification",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.overline,
                    color = White
                )
            }
        }
    }) {
        Icon(
            painter = painterResource(
                id = if (countNotification == 0) R.drawable.ic_notification_bell
                else R.drawable.ic_notification_bell_2
            ),
            contentDescription = null,
            tint = White
        )
    }
}
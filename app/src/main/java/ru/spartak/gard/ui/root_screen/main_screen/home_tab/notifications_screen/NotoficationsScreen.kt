package ru.spartak.gard.ui.root_screen.main_screen.home_tab.notifications_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.Dark100
import ru.spartak.gard.ui.theme.Dark200
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@Composable
fun NotificationsScreen(navController: NavController) {
    GardTheme {
        Box(modifier = Modifier
            .fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                NotificationsTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(41.dp)
                ) {
                    navController.navigateUp()
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
                (0..2).forEach { _ ->
                    NotificationItem(
                        modifier = Modifier.fillMaxWidth(),
                        iconId = R.drawable.fortnite_logo,
                        enabled = true,
                        subtitle = "Congratulations with your first task!",
                        text = "Good job! This time we give you a bonus 40 points. Keep your tempo!"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }
                (0..1).forEach { _ ->
                    NotificationItem(
                        modifier = Modifier.fillMaxWidth(),
                        iconId = R.drawable.fortnite_logo,
                        enabled = false,
                        subtitle = "You’ve reached the tier 1!!",
                        text = "Congratulations on your achievement! Keep completing tasks and earn points faster with each level!"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(Dark100)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                AllReadBtn(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                        .height(40.dp), onClick = {}
                )
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Dark200)
            }
        }
    }
}

@Composable
fun AllReadBtn(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Make all as read",
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Composable
fun NotificationsTopBar(modifier: Modifier, backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(R.string.notifications),
        modifier = modifier,
        leftView = {
            BackBtn {
                backOnClick()
            }
        })
}

@Composable
fun NotificationItem(
    modifier: Modifier,
    iconId: Int,
    enabled: Boolean,
    subtitle: String,
    text: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null
            )
            if (enabled)
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                        .padding(top = 2.dp, end = 2.dp)
                        .align(Alignment.TopEnd)
                        .size(8.dp)
                )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = text, style = MaterialTheme.typography.body2)
        }
    }
}

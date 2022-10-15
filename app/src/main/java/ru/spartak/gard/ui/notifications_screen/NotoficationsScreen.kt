package ru.spartak.gard.ui.notifications_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@Composable
fun NotificationsScreen(navController: NavController) {
    GardTheme {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            NotificationsTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp)
            ) {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
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
        Box(modifier = Modifier.size(48.dp)) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null
            )
            if (enabled)
                Box(
                    modifier = modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                        .align(Alignment.TopEnd)
                        .padding(top = 2.dp, end = 2.dp)
                        .size(8.dp)
                )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = text, style = MaterialTheme.typography.body2)
        }
    }
}

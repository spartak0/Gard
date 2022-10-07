package ru.spartak.gard.ui.profile_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.EditBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {
    GardTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            val visibleToast = remember { mutableStateOf(false) }
            AnimatedVisibility(visible = visibleToast.value) {
                Toast(
                    iconId = R.drawable.ic_check_mark,
                    backgroundColor = Success500,
                    text = "Copied",
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small,
                            top = 20.dp,
                            bottom = MaterialTheme.spacing.small
                        )
                        .fillMaxWidth()
                        .height(45.dp)

                )
            }
            AnimatedVisibility(visible = !visibleToast.value) {
                ProfileTopBar(
                    backOnClick = { navController.navigateUp() },
                    editOnClick = { navController.navigate(Screen.EditScreen.route) },
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.spacing.small,
                            bottom = MaterialTheme.spacing.mediumLarge
                        )
                        .fillMaxWidth()
                        .height(41.dp)

                )
            }
            Username(text = "Nagibator228")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
            Email(text = "123@mail.ru")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            RefferalsCard(refferalCode = "FF33GG", visibleToast)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SettingsItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            LevelItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            FAQItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PoliceItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LogOutItem(onClick = {})
        }
    }
}

@Composable
fun Toast(iconId: Int, backgroundColor: Color, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.background(backgroundColor, RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
        Icon(painter = painterResource(id = iconId), contentDescription = null, tint = Text50)
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun SettingsItem(onClick: () -> Unit) {
    AlsoItem(
        title = stringResource(R.string.settings),
        iconId = R.drawable.ic_settings,
        onClick = onClick
    )
}

@Composable
fun LevelItem(onClick: () -> Unit) {
    AlsoItem(title = stringResource(R.string.level), iconId = R.drawable.ic_out, onClick = onClick)
}

@Composable
fun FAQItem(onClick: () -> Unit) {
    AlsoItem(title = stringResource(R.string.faq), iconId = R.drawable.ic_out, onClick = onClick)
}

@Composable
fun PoliceItem(onClick: () -> Unit) {
    AlsoItem(title = stringResource(R.string.police), iconId = R.drawable.ic_out, onClick = onClick)
}

@Composable
fun LogOutItem(onClick: () -> Unit) {
    AlsoItem(title = stringResource(R.string.log_out), textColor = Error500, onClick = onClick)
}

@Composable
fun AlsoItem(
    title: String,
    textColor: Color = Text50,
    iconId: Int? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(start = MaterialTheme.spacing.smallMedium),
            color = textColor
        )
        iconId?.let {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.smallMedium)
                    .size(20.dp)

            )
        }
    }
}

@Composable
fun Email(text: String) {
    Text(text = text, style = MaterialTheme.typography.body1)

}

@Composable
fun Username(text: String) {
    Text(text = text.uppercase(), style = MaterialTheme.typography.h5)
}

@Composable
fun ProfileTopBar(backOnClick: () -> Unit, editOnClick: () -> Unit, modifier: Modifier = Modifier) {
    TopBar(
        subtitleText = stringResource(R.string.profile),
        leftView = { BackBtn { backOnClick() } },
        rightView = { EditBtn { editOnClick() } },
        modifier = modifier
    )

}
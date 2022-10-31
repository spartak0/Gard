package ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.EditBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.bottomAlign
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant

@SuppressLint(
    "CoroutineCreationDuringComposition", "RememberReturnType",
    "UnrememberedMutableState"
)
@Composable
fun ProfileScreen(
    navController: NavController,
    showSaveToast: Boolean = false,
    levelOnClick: () -> Unit
) {

    val visibleToast = remember { mutableStateOf(false) }
    val visibleSaveToast = mutableStateOf(showSaveToast)
    val visibleDialog = remember { mutableStateOf(false) }

    GardTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            AnimatedVisibility(visible = visibleSaveToast.value) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                }
                Toast(
                    iconId = R.drawable.ic_check_mark,
                    backgroundColor = Success500,
                    text = if (showSaveToast) stringResource(R.string.saved) else stringResource(
                        R.string.saved
                    ),
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small,
                            top = 20.dp,
                            bottom = MaterialTheme.spacing.small
                        )
                        .fillMaxWidth()
                        .height(45.dp),
                    dismiss = {
                        visibleSaveToast.value = false
                        navController.previousBackStackEntry?.arguments?.putAll(bundleOf(Constant.SAVE_TOAST_KEY to false))
                    }
                )

            }

            AnimatedVisibility(visible = visibleToast.value) {
                Toast(
                    iconId = R.drawable.ic_check_mark,
                    backgroundColor = Success500,
                    text = if (showSaveToast) stringResource(R.string.saved) else stringResource(
                        R.string.copied
                    ),
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small,
                            top = 20.dp,
                            bottom = MaterialTheme.spacing.small
                        )
                        .fillMaxWidth()
                        .height(45.dp),
                    dismiss = {
                        visibleToast.value = false
                    }
                )
            }
            AnimatedVisibility(visible = !visibleToast.value && !visibleSaveToast.value) {
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
            SettingsItem(onClick = { navController.navigate(Screen.SettingsScreen.route) })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            LevelItem(onClick = { levelOnClick() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            FAQItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PoliceItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LogOutItem(onClick = { visibleDialog.value = true })
        }
        if (visibleDialog.value) {
            LogOutDialog(
                showDialog = visibleDialog,
                modifier = Modifier
                    .bottomAlign()
                    .padding(bottom = 40.dp)
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            ) {
                //todo log out on click
            }
        }
    }
}


@Composable
fun LogOutDialog(showDialog: MutableState<Boolean>, modifier: Modifier, onClick: () -> Unit) {

        ru.spartak.gard.ui.details.Dialog(
            subtitle = stringResource(id = R.string.sure_log_out),
            text = stringResource(id = R.string.transfered_to_starting),
            confirmText = stringResource(id = R.string.log_out),
            rejectText = stringResource(id = R.string.cancel),
            showDialog = showDialog,
            modifier = modifier,
            onClick = onClick
        )
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Toast(
    iconId: Int,
    backgroundColor: Color,
    text: String,
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
) {
    Row(
        modifier = modifier.background(backgroundColor, RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
        Icon(painter = painterResource(id = iconId), contentDescription = null, tint = Text50)
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = text, style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
        )
    }
    CoroutineScope(Dispatchers.IO).launch {
        delay(2000)
        dismiss()
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
    title: String, textColor: Color = Text50, iconId: Int? = null, onClick: () -> Unit
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
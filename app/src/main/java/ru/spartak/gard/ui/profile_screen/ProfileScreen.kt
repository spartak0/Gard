package ru.spartak.gard.ui.profile_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant
import kotlin.math.log

@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType",
    "UnrememberedMutableState"
)
@Composable
fun ProfileScreen(
    navController: NavController,
    showSaveToast: Boolean = false,
    levelOnClick:()->Unit
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
                        visibleSaveToast.value=false
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
                        visibleToast.value=false
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
            LevelItem(onClick = { levelOnClick()})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            FAQItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            PoliceItem(onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LogOutItem(onClick = { visibleDialog.value = true })
            if (visibleDialog.value) {
                LogOutDialog(
                    showDialog = visibleDialog,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(bottom = 40.dp)
                        .bottomAlign()
                        .fillMaxWidth()
                ) {
                    //todo log out on click
                }
            }

        }
    }
}


fun Modifier.bottomAlign() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints);
    layout(constraints.maxWidth, constraints.maxHeight) {
        placeable.place(0, constraints.maxHeight - placeable.height, 10f)
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogOutDialog(showDialog: MutableState<Boolean>, modifier: Modifier, onClick: () -> Unit) {
    Dialog(
        onDismissRequest = { showDialog.value = false },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colors.secondary, RoundedCornerShape(4.dp)),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = stringResource(R.string.sure_log_out),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.fillMaxWidth(), color = Muted700, thickness = 1.dp)
            Text(
                text = stringResource(R.string.transfered_to_starting),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.fillMaxWidth(), color = Muted700, thickness = 1.dp)
            Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                Box(
                    modifier = Modifier
                        .height(41.dp)
                        .border(1.dp, Muted700, RoundedCornerShape(4.dp))
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            color = MaterialTheme.colors.secondary
                        )
                        .clickable {
                            showDialog.value = false
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Box(
                    modifier = Modifier
                        .height(41.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Error600)
                        .clickable { onClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Log Out",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Toast(
    iconId: Int,
    backgroundColor: Color,
    text: String,
    modifier: Modifier = Modifier,
    dismiss:()->Unit,
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
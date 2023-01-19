package ru.spartak.gard.ui.root_screen.main_screen.home_tab.settings_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.LocaleHelper


@Composable
fun SettingsScreen(navController: NavController) {
    val checkNotify = remember { mutableStateOf(false) }
    val checkContacts = remember { mutableStateOf(false) }
    val checkMicrophone = remember { mutableStateOf(false) }
    val checkLocation = remember { mutableStateOf(false) }
    val checkMedia = remember { mutableStateOf(false) }
    val showLanguageDialog = remember { mutableStateOf(false) }

    GardTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SettingsTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp)
            ) {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            SubtitleGeneral()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            ChangeLanguageView(context = LocalContext.current, onClick = {
                showLanguageDialog.value = true
            })
            if (showLanguageDialog.value) SelectLanguageDialog(
                showDialog = showLanguageDialog,
                context = LocalContext.current,
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitleNotifications()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SwitchSettingsItem(text = "Allow GARD notify you", checkState = checkNotify)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitleControls()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SwitchSettingsItem(text = stringResource(R.string.contacts), checkState = checkContacts)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SwitchSettingsItem(text = stringResource(R.string.microphone), checkState = checkMicrophone)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SwitchSettingsItem(text = stringResource(R.string.location), checkState = checkLocation)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            SwitchSettingsItem(text = stringResource(R.string.media), checkState = checkMedia)
        }
    }
}

@Composable
fun SwitchSettingsItem(text: String, checkState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
        )
        CustomSwitch(checkState = checkState)
    }
}

@Composable
fun CustomSwitch(
    checkState: MutableState<Boolean>,
    width: Dp = 48.dp,
    height: Dp = 24.dp,
    trackColor: Color = Text50,
    backgroundSelectedColor: Color = Primary600,
    backgroundUnselectedColor: Color = Muted700,
) {
    val gapBetweenThumbAndTrackEdge = 2.dp
    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge
    val animatePosition = animateFloatAsState(
        targetValue = if (checkState.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        checkState.value = !checkState.value
                    }
                )
            }
    ) {
        drawRoundRect(
            color = if (checkState.value) backgroundSelectedColor else backgroundUnselectedColor,
            cornerRadius = CornerRadius(x = 12.dp.toPx(), y = 12.dp.toPx()),
        )

        drawCircle(
            color = trackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}


@Composable
fun SubtitleControls() {
    Text(text = "Controls", style = MaterialTheme.typography.body2, color = Text500)
}

@Composable
fun SubtitleNotifications() {
    Text(text = "Notifications", style = MaterialTheme.typography.body2, color = Text500)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectLanguageDialog(showDialog: MutableState<Boolean>, context: Context, modifier: Modifier) {
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            LocaleHelper.getLocaleCode(
                context = context
            )
        )
    }
    Dialog(
        onDismissRequest = { showDialog.value = false },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colors.secondary)
                .selectableGroup()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MaterialTheme.spacing.medium),
        ) {
            LocaleHelper.languages.forEach { entry ->
                Row(
                    Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .clickable {
                            onOptionSelected(entry.key)
                            LocaleHelper.setLocale(context, entry.key)
                        }
                        .height(56.dp), verticalAlignment = Alignment.CenterVertically)
                {
                    Surface(
                        shape = CircleShape,
                        border = BorderStroke(
                            2.dp,
                            if (entry.key == selectedOption) Text50 else Muted700
                        ),
                        color = Color.Transparent,
                        modifier = Modifier.size(20.dp)
                    ) {
                        if (entry.key == selectedOption) Surface(
                            shape = CircleShape,
                            color = Text50,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize()
                        ) {}
                    }
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))
                    Text(
                        text = entry.value,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
                    )


                }
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ChangeLanguageView(context: Context, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            }
    ) {
        Column {
            Text(
                text = stringResource(R.string.language),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
            )
            val tmpLocale = LocaleHelper.getLocaleName(context)
            val locale = "${tmpLocale.substring(0, 1).uppercase()}${tmpLocale.substring(1)}"
            Text(
                text = locale,
                style = MaterialTheme.typography.overline.copy(letterSpacing = 0.sp),
                color = Text500
            )

        }
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
    }
}


@Composable
fun SubtitleGeneral() {
    Text(
        text = stringResource(R.string.General),
        style = MaterialTheme.typography.body2,
        color = Text500
    )
}

@Composable
fun SettingsTopBar(modifier: Modifier, backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(id = R.string.settings),
        modifier = modifier,
        leftView = {
            BackBtn {
                backOnClick()
            }
        })
}
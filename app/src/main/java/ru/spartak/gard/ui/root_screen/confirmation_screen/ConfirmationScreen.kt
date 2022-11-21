package ru.spartak.gard.ui.root_screen.confirmation_screen

import android.annotation.SuppressLint
import android.text.format.DateUtils
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.topAlign
import ru.spartak.gard.ui.navigation.Graphs
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.navigation.navigate
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.Toast
import ru.spartak.gard.ui.theme.*
import ru.spartak.gard.utils.Constant
import ru.spartak.gard.utils.StatusBarHeight
import kotlin.time.Duration.Companion.seconds

@Composable
fun ConfirmationScreen(navController: NavController) {
    val text = remember {
        mutableStateOf("")
    }
//    val isError = remember{ mutableStateOf(false)}
//    val isSendCode = remember{ mutableStateOf(false)}
    val toastState = remember { mutableStateOf(Triple(false,ToastState.Success as ToastState, "")) }
//    val toast = isError.value or isSendCode.value
    val borderColor = animateColorAsState(
        targetValue = if ( toastState.value.first && toastState.value.second == ToastState.Error
        ) Error600 else Tertiary500
    )

    GardTheme {
        Column {
            Spacer(modifier = Modifier.height(38.dp + MaterialTheme.spacing.small))
            ConfirmationTopBar(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Image(
                painter = painterResource(id = R.drawable.confirm),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            SubtitleConfirmation()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            TextConfirmation()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            CodeTextField(
                text = text,
                code = "123456",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(40.dp),
                borderColor = SolidColor(borderColor.value),
                onSuccess = {
                    navController.navigate(
                        Graphs.Main,
                        bundleOf(
                            Constant.MAIN_GRAPH_START_DESTINATION to Screen.ProfileScreen.route,
                            Constant.SAVE_TOAST_KEY to true
                        )
                    )
                },
                onError = {
                    toastState.value = Triple(true ,ToastState.Error,"Wrong code")
                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SendAgainBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp),
                onClick = {
                    toastState.value = Triple(true,ToastState.Info,"Another code was sended to your E-Mail")
                }
            )
        }
        AnimatedVisibility(
            visible = toastState.value.first,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Toast(
                iconId = toastState.value.second.iconId,
                backgroundColor = toastState.value.second.color,
                text = toastState.value.third,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small,
                        top = StatusBarHeight.calculate() + 20.dp,
                    )
                    .fillMaxWidth()
                    .height(45.dp)
                    .topAlign(),
                dismiss = {
                    toastState.value = toastState.value.copy(first = false)
                }
            )

        }

    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun SendAgainBtn(modifier: Modifier, onClick: () -> Unit) {
    val tick = remember {
        mutableStateOf(10)
    }
    LaunchedEffect(Unit) {
        while (tick.value > 0) {
            delay(1.seconds)
            tick.value--
        }
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(enabled = (tick.value == 0), onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        val formatTime = "(${DateUtils.formatElapsedTime(tick.value.toLong())})"
        Text(
            text = "Send  code again ${if (tick.value != 0) formatTime else ""}",
            style = MaterialTheme.typography.body2,
            color = if (tick.value == 0) Tertiary500 else Tertiary500.copy(alpha = 0.5F)
        )
    }
}

@Composable
fun CodeTextField(
    text: MutableState<String>,
    code: String,
    borderColor: Brush,
    onSuccess: () -> Unit,
    onError: () -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            if (it.length <= 6) text.value = it
            if (text.value.length == code.length) {
                if (text.value == code) onSuccess()
                else {
                    onError()
                }
            }
        },
        modifier = modifier
            .border(
                width = 2.dp,
                brush = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .background(brush = SolidColor(Color(0xFF059669).copy(0.1F))),
        cursorColor = SolidColor(Tertiary500)
    )
}

@Composable
fun TextConfirmation() {
    Text(
        text = "We’ve send a confirmation code to your new E-mail.\nType it to proceed",
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
    )
}

@Composable
fun SubtitleConfirmation() {
    Text(
        text = "Confirmation",
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h6.copy(fontSize = 30.sp)
    )
}

@Composable
fun ConfirmationTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(R.string.confirmation),
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}

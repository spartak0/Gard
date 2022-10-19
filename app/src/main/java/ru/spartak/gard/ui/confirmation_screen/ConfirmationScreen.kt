package ru.spartak.gard.ui.confirmation_screen

import android.annotation.SuppressLint
import android.text.format.DateUtils
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Tertiary500
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.spacing
import kotlin.time.Duration.Companion.seconds

@Composable
fun ConfirmationScreen(navController: NavController) {
    val text = remember {
        mutableStateOf("")
    }

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
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SendAgainBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp),
                onClick = {}
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
            color = if (tick.value == 0) Text50 else Tertiary500
        )
    }
}

@Composable
fun CodeTextField(text: MutableState<String>, modifier: Modifier) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = modifier
            .border(
                width = 2.dp,
                brush = SolidColor(Color(0xFF00DEC8)),
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .background(brush = SolidColor(Color(0xFF059669).copy(0.1F))),
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
        subtitleText = stringResource(id = R.string.edit),
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}

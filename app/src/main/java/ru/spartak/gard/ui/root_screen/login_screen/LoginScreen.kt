package ru.spartak.gard.ui.root_screen.login_screen

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.details.topAlign
import ru.spartak.gard.ui.navigation.BottomScreen
import ru.spartak.gard.ui.navigation.RootScreen
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.navigation.navigate
import ru.spartak.gard.ui.root_screen.confirmation_screen.ToastState
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ProceedBtn
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.profile_screen.Toast
import ru.spartak.gard.ui.theme.Error600
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Tertiary500
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.Constant
import ru.spartak.gard.utils.StatusBarHeight

@Composable
fun LoginScreen(navController: NavController) {
    val text = remember {
        mutableStateOf("")
    }
    val toastState =
        remember { mutableStateOf(Triple(false, ToastState.Success as ToastState, "")) }
    val borderColor = animateColorAsState(
        targetValue = if (toastState.value.first && toastState.value.second == ToastState.Error
        ) Error600 else Tertiary500
    )
    val loadStateBtn = remember { mutableStateOf(false) }
    GardTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(38.dp + MaterialTheme.spacing.small))
            LoginTopBar(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Image(
                painter = painterResource(id = R.drawable.game_equipments),
                contentDescription = null,
                modifier = Modifier.size(274.dp, 118.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(
                text = "Type your phone number",
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                style = MaterialTheme.typography.h6.copy(fontSize = 30.sp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = "Use your phone number to both log in and sign in to GARD. Use only your actual mail",
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LoginTextField(
                text = text,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(40.dp),
                borderColor = SolidColor(borderColor.value),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            ProceedBtn(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(41.dp), loadState = loadStateBtn.value
            ) {
                CoroutineScope(Dispatchers.Main).launch {
                    loadStateBtn.value = true
                    navController.navigate(
                        RootScreen.Confirmation.route, bundleOf(
                            Constant.CONFIRMATION_BUNDLE to bundleOf(
                                Constant.MAIN_GRAPH_START_DESTINATION to BottomScreen.HomeScreen.route,
                            )
                        )
                    )
                    loadStateBtn.value = false
                }
            }
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

@Composable
fun LoginTextField(
    text: MutableState<String>,
    borderColor: Brush,
    modifier: Modifier
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
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
fun LoginTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = "",
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(41.dp),
        leftView = { BackBtn { backOnClick() } },
    )
}

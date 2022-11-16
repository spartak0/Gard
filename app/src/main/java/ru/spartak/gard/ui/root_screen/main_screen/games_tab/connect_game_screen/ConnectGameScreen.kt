package ru.spartak.gard.ui.root_screen.main_screen.games_tab.connect_game_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import ru.spartak.gard.ui.details.CustomTextField
import ru.spartak.gard.ui.details.LoadBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.navigation.navigate
import ru.spartak.gard.ui.root_screen.main_screen.games_tab.games_screen.ConnectionStatus
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.Tertiary500
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.Constant

@Composable
fun ConnectGameScreen(navController: NavController) {
    val nickname = remember { mutableStateOf("") }
    val loadState = remember { mutableStateOf(false) }

    GardTheme {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            ConnectGameTopBar { navController.navigateUp() }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Image(
                painter = painterResource(id = R.drawable.fortnite_logo),
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth(),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Text(
                text = "Type your nickname",
                style = MaterialTheme.typography.h5.copy(fontSize = 30.sp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = "Use your actual nickname in Fortnite to connect game to your GARD games",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            CustomTextField(
                value = nickname.value,
                onValueChange = { text -> nickname.value = text },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                borderColor = Tertiary500,
                cursorColor = Tertiary500,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
            LoadBtn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp), onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        //todo remove in vm
                        loadState.value=true
                        val tmp = CoroutineScope(Dispatchers.IO).launch {
                            delay(2000)
                        }
                        tmp.join()
                        navController.navigate(
                            Screen.DetailScreen.route,
                            bundleOf(
                                Constant.GAME_STATUS_KEY to ConnectionStatus.CONNECTED,
                                Constant.START_VIEW_PAGER_TAB to 1
                            )
                        )
                        loadState.value=false
                    }
                }, text = "Proceed", loadState = loadState.value
            )
        }
    }
}

@Composable
fun ConnectGameTopBar(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(R.string.connect_game),
        leftView = {
            BackBtn { backOnClick() }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
    )
}
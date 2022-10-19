package ru.spartak.gard.ui.levels_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@Composable
fun LevelsScreen(navController: NavController) {
    GardTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .verticalScroll(state = rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            TopBarLevels(backOnClick = { navController.navigateUp() })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            Progress(Level.Newbie, 4900)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))
            LevelCard(Level.Newbie, LevelStatus.Current, "0")
            Spacer(modifier = Modifier.height(16.dp))
            LevelCard(Level.Newbie, LevelStatus.Default, "1")
            Spacer(modifier = Modifier.height(16.dp))
            LevelCard(Level.Newbie, LevelStatus.Completed, "2")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun TopBarLevels(backOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(id = R.string.levels),
        leftView = {
            BackBtn { backOnClick() }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
    )
}
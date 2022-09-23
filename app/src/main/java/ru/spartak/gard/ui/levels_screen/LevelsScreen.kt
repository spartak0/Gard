package ru.spartak.gard.ui.levels_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(41.dp)){
                Text(text = "123", modifier = Modifier.align(Alignment.Center))
                Text(text = "123", modifier = Modifier.align(Alignment.CenterStart))
            }
        }
    }
}
package ru.spartak.gard.ui.profile_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.BackBtn
import ru.spartak.gard.ui.details.EditBtn
import ru.spartak.gard.ui.details.TopBar
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.spacing

@Composable
fun ProfileScreen(navController: NavController) {
    GardTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            ProfileTopBar(backOnClick = { navController.navigateUp() }, editOnClick = {})
        }
    }
}

@Composable
fun ProfileTopBar(backOnClick: () -> Unit, editOnClick: () -> Unit) {
    TopBar(
        subtitleText = stringResource(R.string.profile),
        leftView = { BackBtn { backOnClick() } },
        rightView = { EditBtn { editOnClick } }
    )

}
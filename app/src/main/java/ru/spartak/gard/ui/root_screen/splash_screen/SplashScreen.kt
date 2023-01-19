package ru.spartak.gard.ui.root_screen.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spartak.gard.R
import ru.spartak.gard.ui.details.GifImage
import ru.spartak.gard.ui.root_screen.RootScreen
import ru.spartak.gard.ui.root_screen.navigation.*
import ru.spartak.gard.ui.theme.Dark50
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.utils.Constant

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController, viewModel: SplashScreenViewModel = hiltViewModel()) {
    GardTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GifImage(imageID = R.drawable.splash_logo_gif, modifier = Modifier.fillMaxWidth())
        }
    }
    CoroutineScope(Dispatchers.Main).launch {
        delay(2000)
        val currentUser = viewModel.getCurrentUser()
        if (currentUser == null) navController.navigate(RootScreen.Login.route) else navController.navigate(
            Graphs.Main,
            bundleOf(
                Constant.MAIN_GRAPH_START_DESTINATION to BottomScreen.HomeScreen.route
            )
        )
    }
}
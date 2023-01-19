package ru.spartak.gard.ui.root_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.ui.root_screen.navigation.RootNavGraph
import ru.spartak.gard.ui.root_screen.navigation.RootScreen
import ru.spartak.gard.ui.theme.GardTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen() {
    GardTheme {
        Scaffold() {
            RootNavGraph(
                navController = rememberNavController(),
                startDestination = RootScreen.Splash.route,
            )
        }
    }
}
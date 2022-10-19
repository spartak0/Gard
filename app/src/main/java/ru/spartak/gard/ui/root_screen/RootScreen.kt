package ru.spartak.gard.ui.root_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.ui.main_screen.BottomBar
import ru.spartak.gard.ui.navigation.BottomScreen
import ru.spartak.gard.ui.navigation.Graphs
import ru.spartak.gard.ui.navigation.MainNavGraph
import ru.spartak.gard.ui.navigation.RootNavGraph
import ru.spartak.gard.ui.theme.GardTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen(){
    GardTheme {
        Scaffold() {
            RootNavGraph(
                navController = rememberNavController(),
                startDestination = Graphs.Main,
            )
        }
    }
}
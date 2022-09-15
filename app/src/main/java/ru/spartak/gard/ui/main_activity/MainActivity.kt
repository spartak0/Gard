package ru.spartak.gard.ui.main_activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.ui.main_screen.MainScreen
import ru.spartak.gard.ui.navigation.NavGraph
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.GardTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GardTheme {
                MainScreen()
            }
        }
    }
}
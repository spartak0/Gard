package ru.spartak.gard.ui.main_activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.spartak.gard.ui.main_screen.MainScreen
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
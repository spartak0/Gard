package ru.spartak.gard.ui.main_activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.spartak.gard.ui.main_screen.MainScreen
import ru.spartak.gard.ui.root_screen.RootScreen
import ru.spartak.gard.utils.LocaleHelper
import ru.spartak.gard.ui.theme.GardTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GardTheme {
                RootScreen()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocaleHelper.setLocale(newBase, LocaleHelper.getLocaleCode(newBase))
        )
    }
}

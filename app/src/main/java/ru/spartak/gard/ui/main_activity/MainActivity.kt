package ru.spartak.gard.ui.main_activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import dagger.hilt.android.AndroidEntryPoint
import ru.spartak.gard.ui.root_screen.RootScreen
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.utils.LocaleHelper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalActivity provides this){
                GardTheme {
                    RootScreen()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocaleHelper.setLocale(newBase, LocaleHelper.getLocaleCode(newBase))
        )
    }
}


val LocalActivity = staticCompositionLocalOf<ComponentActivity> {
    noLocalProvidedFor("LocalActivity")
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
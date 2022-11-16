package ru.spartak.gard.ui.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ru.spartak.gard.utils.LocaleHelper

@HiltAndroidApp
class App : Application() {
    override fun attachBaseContext(base: Context) {
        val language = LocaleHelper.getLocaleCode(base)
        super.attachBaseContext(LocaleHelper.setLocale(base, language))
    }
}
package ru.spartak.gard.ui.app

import android.app.Application
import android.content.Context
import ru.spartak.gard.ui.settings_screen.LocaleHelper

class App : Application() {
    override fun attachBaseContext(base: Context) {
        val language = LocaleHelper.getLocaleCode(base)
        super.attachBaseContext(LocaleHelper.setLocale(base, language))
    }
}
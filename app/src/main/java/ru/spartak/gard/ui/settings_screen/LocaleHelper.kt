package ru.spartak.gard.ui.settings_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import ru.spartak.gard.utils.Constant
import java.util.*

object LocaleHelper {
    val languages = mapOf(
        "ru" to "Русский",
        "en" to "English",
    )

    @SuppressLint("CommitPrefEdits")
    fun setLocale(context: Context, languageCode: String): Context? {
        updateSharedPreferences(context, languageCode)
        val newContext = updateResources(context, languageCode)
        (context as? Activity)?.recreate()
        return newContext
    }

    private fun updateSharedPreferences(context: Context, language: String) {
        val sharedPreferences = context.getSharedPreferences(
            Constant.languageSharedPreferences,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(Constant.language, language)
        editor.apply()
    }

    fun getLocaleCode(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(
            Constant.languageSharedPreferences,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(Constant.language, "en") ?: "en"
    }

    fun getLocaleName(context: Context): String {
        return languages[getLocaleCode(context)]?:""
    }

    private fun updateResources(context: Context, languageCode: String): Context? {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }


}
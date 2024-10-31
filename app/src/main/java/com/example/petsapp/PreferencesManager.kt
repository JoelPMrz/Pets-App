package com.example.petsapp

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    var isDarkMode: Boolean
        get() = prefs.getBoolean("dark_mode", false)
        set(value) {
            prefs.edit().putBoolean("dark_mode", value).apply()
        }
}

package com.example.petsapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider


class ThemeViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {
    var isDarkMode by mutableStateOf(preferencesManager.isDarkMode)
        private set

    fun toggleDarkMode() {
        isDarkMode = !isDarkMode
        preferencesManager.isDarkMode = isDarkMode
    }
}


class ThemeViewModelFactory(private val preferencesManager: PreferencesManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(preferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

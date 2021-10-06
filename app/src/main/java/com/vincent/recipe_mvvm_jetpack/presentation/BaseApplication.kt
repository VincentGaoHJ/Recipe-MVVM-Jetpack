package com.vincent.recipe_mvvm_jetpack.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp


/**
 * Hilt Generates the components for you automatically and then you define modules
 * and install the dependencies into those generated components
 */
@HiltAndroidApp
class BaseApplication : Application() {
    val isDark = mutableStateOf(false)

    fun toggleLightTheme() {
        isDark.value = !isDark.value
    }
}
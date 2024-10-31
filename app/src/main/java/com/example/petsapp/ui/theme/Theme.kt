package com.example.petsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryColor = Color(0xFFEB722F)
private val DarkSurfaceColor = Color(0xFF2C2C2C)
private val LightSurfaceColor = Color(0xFFF8F8F8)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,
    surface = DarkSurfaceColor,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.Black,
    surface = LightSurfaceColor,
    onSurface = Color.Black
)

@Composable
fun PetsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

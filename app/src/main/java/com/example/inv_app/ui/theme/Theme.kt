package com.example.inv_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    inversePrimary = Green40,
    secondary = GreenGray80,
    onSecondary = GreenGray20,
    secondaryContainer = GreenGray30,
    onSecondaryContainer = GreenGray90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    background = Gray10,
    onBackground = Gray90,
    surface = Gray10,
    onSurface = Gray90,
    surfaceVariant = DarkGreenGray30,
    onSurfaceVariant = DarkGreenGray80,
    surfaceTint = Green80,
    inverseSurface = Gray90,
    inverseOnSurface = Gray20,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    outline = DarkGreenGray60,
    outlineVariant = DarkGreenGray30,
    scrim = Gray0
)

private val LightColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Green100,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    inversePrimary = Green80,
    secondary = GreenGray40,
    onSecondary = GreenGray100,
    secondaryContainer = GreenGray90,
    onSecondaryContainer = GreenGray10,
    tertiary = Blue40,
    onTertiary = Blue100,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    background = Gray90,
    onBackground = Gray10,
    surface = Gray90,
    onSurface = Gray10,
    surfaceVariant = DarkGreenGray90,
    onSurfaceVariant = DarkGreenGray30,
    surfaceTint = Green40,
    inverseSurface = Gray20,
    inverseOnSurface = Gray90,
    error = Red40,
    onError = Red100,
    errorContainer = Red90,
    onErrorContainer = Red10,
    outline = DarkGreenGray50,
    outlineVariant = DarkGreenGray80,
    scrim = Gray0
)

@Composable
fun InvAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        */
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content, shapes = shapes().roundedShapes
    )
}
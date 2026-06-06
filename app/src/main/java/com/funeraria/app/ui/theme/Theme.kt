package com.funeraria.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary            = PrimaryPurple,
    onPrimary          = Color.White,
    primaryContainer   = SoftPurple,
    onPrimaryContainer = TextPrimary,

    secondary          = AccentGold,
    onSecondary        = Color.Black,
    secondaryContainer = SoftPurple,
    onSecondaryContainer = TextPrimary,

    tertiary           = TextSecondary,
    onTertiary         = Color.White,
    tertiaryContainer  = SoftPurple,
    onTertiaryContainer = PrimaryPurple,

    background         = BackgroundColor,
    onBackground       = TextPrimary,

    surface            = SurfaceColor,
    onSurface          = TextPrimary,
    surfaceVariant     = SurfaceElevated,
    onSurfaceVariant   = TextSecondary,

    outline            = Border,
    outlineVariant     = DividerColor,

    error              = ErrorColor,
    onError            = Color.White,
    errorContainer     = SoftPurple,
    onErrorContainer   = ErrorColor
)

@Composable
fun FunerariaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography  = Typography,
        shapes      = Shapes,
        content     = content
    )
}

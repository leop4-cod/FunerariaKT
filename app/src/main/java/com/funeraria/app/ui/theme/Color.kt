package com.funeraria.app.ui.theme

import androidx.compose.ui.graphics.Color

// Primary - Deep Purple / Dark Elegant
val PrimaryDark      = Color(0xFF1A1A2E)
val PrimaryPurple    = Color(0xFF6C63FF)
val DarkSurface      = Color(0xFF16213E)
val AccentGold       = Color(0xFFD4AF37)
val SoftPurple       = Color(0xFF2D2B55)

// Surfaces
val BackgroundColor  = Color(0xFF0F0F1A)
val SurfaceColor     = Color(0xFF1A1A2E)
val SurfaceElevated  = Color(0xFF222240)
val PageBg           = Color(0xFF141428)

// Text
val TextPrimary      = Color(0xFFE8E8F0)
val TextSecondary    = Color(0xFF9090A8)
val TextTertiary     = Color(0xFF5A5A70)
val TextOnDark       = Color(0xFFFFFFFF)
val TextFaint        = TextTertiary

// Semantic
val Success          = Color(0xFF4CAF50)
val ErrorColor       = Color(0xFFEF5350)
val Warning          = Color(0xFFFFA726)
val Info             = Color(0xFF42A5F5)
val PremiumGold      = Color(0xFFD4AF37)

// Borders
val Border           = Color(0xFF2A2A45)
val BorderFocus      = Color(0xFF6C63FF)
val DividerColor     = Color(0xFF1F1F38)

// Aliases
val LightRed         = SoftPurple
val PrimaryRed       = ErrorColor

// Gradients
val PurpleGradient      = listOf(PrimaryPurple, Color(0xFF5A52E0))
val DarkGradient        = listOf(Color(0xFF0F0F1A), Color(0xFF1A1A2E))
val GoldGradient        = listOf(Color(0xFFD4AF37), Color(0xFFB8860B))
val HeroGradient        = listOf(PrimaryDark, DarkSurface)
val BlueGradient        = PurpleGradient

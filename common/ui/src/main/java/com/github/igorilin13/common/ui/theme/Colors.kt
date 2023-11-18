package com.github.igorilin13.common.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val darkColorScheme = darkColorScheme(
    primary = Color(0xFF083d92),
    secondary = Color(0xFF355D9B),
    tertiary = Color(0xFF627DAB),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0),
)

internal val lightColorScheme = lightColorScheme(
    primary = Color(0xFF083d92),
    secondary = Color(0xFF355D9B),
    tertiary = Color(0xFF627DAB),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF4F4F4),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)
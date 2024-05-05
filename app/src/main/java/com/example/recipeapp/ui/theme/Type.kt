package com.example.recipeapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R

val ProximaNova = FontFamily(
    Font(R.font.mark_simonson_proxima_nova_regular),
    Font(R.font.mark_simonson_proxima_nova_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displayLarge = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
    ),

    displaySmall = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 17.5.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Normal,
        fontSize = 17.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    titleSmall = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)
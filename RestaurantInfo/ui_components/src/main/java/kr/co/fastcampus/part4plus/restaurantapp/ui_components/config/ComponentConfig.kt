package kr.co.fastcampus.part4plus.restaurantapp.ui_components.config

import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.color.ColorSet

data class ComponentConfig(
    val colors: ColorSet,
    val typography: Typography,
    val shapes: Shapes,
    val isDarkTheme: Boolean
)

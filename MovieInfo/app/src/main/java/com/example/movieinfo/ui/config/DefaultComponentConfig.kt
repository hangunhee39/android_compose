package com.example.movieinfo.ui.config

import com.example.movieinfo.ui.theme.Shapes
import com.example.movieinfo.ui.theme.color.ColorSet

//theme(전환) 에서 요구하는 정보
object DefaultComponentConfig {
    val RED_THEME = ComponentConfig(
        colors = ColorSet.Red,
        shapes = Shapes,
        typography = Typography,
        isDarkTheme = false
    )

    val BLUE_THEME = ComponentConfig(
        colors = ColorSet.Blue,
        shapes = Shapes,
        typography = Typography,
        isDarkTheme = false
    )

    private var currentConfig = RED_THEME
    private var themeColorSet: ColorSet = ColorSet.Red
}
package kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.config.ComponentConfig
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.config.DefaultComponentConfig
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.color.ColorSet
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.color.MyColors

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.LightColors }

@Composable
fun RestaurantAppTheme(
    themeState: State<ComponentConfig> = mutableStateOf(
        DefaultComponentConfig.RED_THEME
    ),
    content: @Composable () -> Unit
) {
    val myTheme by remember {
        themeState
    }

    val colors = if (myTheme.isDarkTheme) {
        myTheme.colors.DarkColors
    } else {
        myTheme.colors.LightColors
    }

    CompositionLocalProvider(LocalColors provides colors ) {
        MaterialTheme(
            colors = colors.material,
            typography = myTheme.typography,
            shapes = myTheme.shapes,
            content = content
        )
    }
}

val MaterialTheme.colorScheme: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

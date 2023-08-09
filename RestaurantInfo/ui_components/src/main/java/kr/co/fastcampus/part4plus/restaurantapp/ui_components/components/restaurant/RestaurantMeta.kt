package kr.co.fastcampus.part4plus.restaurantapp.ui_components.components.restaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.Paddings
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.util.getAnnotatedText

@Composable
fun RestaurantMeta(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    Column(modifier = modifier) {
        // Key
        Text(
            text = key,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        )

        // Value
        Text(
            text = getAnnotatedText(text = value),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.height(Paddings.extra))
    }
}

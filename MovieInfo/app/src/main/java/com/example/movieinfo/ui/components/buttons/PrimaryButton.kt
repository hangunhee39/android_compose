package com.example.movieinfo.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieinfo.ui.models.button.LeadingIconData
import com.example.movieinfo.ui.theme.MovieInfoTheme
import com.example.movieinfo.ui.theme.Paddings

private val LEADING_ICON_SIZE = 24.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    leadingIconData: LeadingIconData?= null,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIconData?.let {
                Icon(
                    modifier = Modifier.size(LEADING_ICON_SIZE),
                    painter = painterResource(id = leadingIconData.IconDrawable),
                    contentDescription = stringResource(id = leadingIconData.iconContentDescription)
                )
                Spacer(modifier = Modifier.width(Paddings.small))
            }
            Text(
                //id 값이 있으면 그에 해당하는 값을 불러오고 없으면 text값을 불러옴
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    MovieInfoTheme {
        PrimaryButton(
            text = "summit"
        ) {

        }
    }
}
package com.example.movieinfo.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieinfo.R
import com.example.movieinfo.ui.models.button.LeadingIconData
import com.example.movieinfo.ui.models.dialog.DialogButton
import com.example.movieinfo.ui.theme.MovieInfoTheme

@Preview
@Composable
fun AlertPreView() {
    MovieInfoTheme {
        DialogPopup.Alert(
            title = "title", bodyText = "body", buttons = listOf(
                DialogButton.UnderlinedText("CLOSE")
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreView() {
    MovieInfoTheme {
        DialogPopup.Default(
            title = "title", bodyText = "body", buttons = listOf(
                DialogButton.Primary("OPEN"),
                DialogButton.SecondaryBorderless("CLOSE"),
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreView3() {
    MovieInfoTheme {
        DialogPopup.Rating(
            movieName = "the Ring",
            rating = 5.5f,
            buttons = listOf(
                DialogButton.Primary(
                title = "OPEN",
                    leadingIconData = LeadingIconData(
                        IconDrawable = R.drawable.ic_send,
                        iconContentDescription = 0
                    )
                ),
                DialogButton.SecondaryBorderless("CLOSE"),
            )
        )
    }
}


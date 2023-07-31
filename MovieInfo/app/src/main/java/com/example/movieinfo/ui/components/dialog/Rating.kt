package com.example.movieinfo.ui.components.dialog

import androidx.compose.runtime.Composable
import com.example.movieinfo.ui.models.dialog.DialogButton
import com.example.movieinfo.ui.models.dialog.DialogContent
import com.example.movieinfo.ui.models.dialog.DialogText
import com.example.movieinfo.ui.models.dialog.DialogTitle


@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
){
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate Your Score"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = rating
            )
        ),
        button = buttons
    )
}
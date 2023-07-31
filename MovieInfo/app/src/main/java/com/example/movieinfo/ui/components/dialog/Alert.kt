package com.example.movieinfo.ui.components.dialog

import androidx.compose.runtime.Composable
import com.example.movieinfo.ui.models.dialog.DialogButton
import com.example.movieinfo.ui.models.dialog.DialogContent
import com.example.movieinfo.ui.models.dialog.DialogText
import com.example.movieinfo.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(title),
        dialogContent = DialogContent.Large(
            DialogText.Default(bodyText)
        ),
        button = buttons
    )
}
package com.example.movieinfo.ui.components.dialog

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieinfo.ui.components.dialog.components.button.DialogButtonColumn
import com.example.movieinfo.ui.components.dialog.components.content.DialogContentWrapper
import com.example.movieinfo.ui.components.dialog.components.title.DialogTitleWrapper
import com.example.movieinfo.ui.models.dialog.DialogButton
import com.example.movieinfo.ui.models.dialog.DialogContent
import com.example.movieinfo.ui.models.dialog.DialogText
import com.example.movieinfo.ui.models.dialog.DialogTitle
import com.example.movieinfo.ui.theme.MovieInfoTheme
import com.example.movieinfo.ui.theme.Paddings

//모든 dialog를 맵핑하여 사용
@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    button: List<DialogButton>? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(Paddings.none),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let {
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.xlarge,
                        end = Paddings.xlarge,
                        bottom = Paddings.xlarge
                    )
            ) {
                dialogContent?.let {
                    DialogContentWrapper(it)
                }
                button?.let {
                    DialogButtonColumn(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview() {
    MovieInfoTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Header("title"),
            dialogContent = DialogContent.Large(DialogText.Default("abcabacbacbacba")),
            button = listOf(
                DialogButton.Primary("okay") {

                }
            )
        )
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview2() {
    MovieInfoTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("title"),
            dialogContent = DialogContent.Default(DialogText.Default("abcabacbacbacba")),
            button = listOf(
                DialogButton.Secondary("okay") {

                }
            )
        )
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview3() {
    MovieInfoTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("title"),
            dialogContent = DialogContent.Rating(
                DialogText.Rating(
                    text = "kkkk",
                    rating = 5.5f
                )
            ),
            button = listOf(
                DialogButton.Secondary("okay") {

                }
            )
        )
    }
}



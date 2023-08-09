package kr.co.fastcampus.part4plus.restaurantapp.ui.components.dialog

import androidx.compose.runtime.Composable
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(
            title
        ),
        dialogContent = DialogContent.Large(
            DialogText.Default(
                bodyText
            )
        ),
        buttons = buttons
    )
}

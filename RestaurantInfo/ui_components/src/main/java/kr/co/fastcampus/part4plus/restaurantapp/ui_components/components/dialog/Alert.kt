package kr.co.fastcampus.part4plus.restaurantapp.ui_components.components.dialog

import androidx.compose.runtime.Composable
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogText
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogTitle

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

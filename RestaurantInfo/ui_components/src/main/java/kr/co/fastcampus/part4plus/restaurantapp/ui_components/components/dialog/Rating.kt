package kr.co.fastcampus.part4plus.restaurantapp.ui_components.components.dialog

import androidx.compose.runtime.Composable
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogText
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    restaurantName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = restaurantName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}

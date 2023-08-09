package kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog

sealed class DialogContent {
    data class Default(
        val dialogText: DialogText.Default
    ): DialogContent()

    data class Large(
        val dialogText: DialogText.Default
    ): DialogContent()

    data class Rating(
        val dialogText: DialogText.Rating
    ): DialogContent()
}

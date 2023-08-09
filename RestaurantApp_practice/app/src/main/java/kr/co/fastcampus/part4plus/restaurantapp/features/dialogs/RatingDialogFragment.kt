package kr.co.fastcampus.part4plus.restaurantapp.features.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4plus.restaurantapp.BaseDialogFragment
import kr.co.fastcampus.part4plus.restaurantapp.R
import kr.co.fastcampus.part4plus.restaurantapp.ui.components.dialog.DialogPopup
import kr.co.fastcampus.part4plus.restaurantapp.ui.components.dialog.Rating
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.buttons.LeadingIconData
import kr.co.fastcampus.part4plus.restaurantapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.restaurantapp.ui.theme.RestaurantAppTheme

@AndroidEntryPoint
class RatingDialogFragment : BaseDialogFragment() {

    private val args: RatingDialogFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.apply {
            isCancelable = true
            setCanceledOnTouchOutside(true)
            window?.setBackgroundDrawable(ColorDrawable(requireContext().getColor(android.R.color.transparent)))
        }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                RestaurantAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    DialogPopup.Rating(
                        restaurantName = args.restaurantName,
                        rating = args.rating,
                        buttons = listOf(
                            DialogButton.Primary(
                                title = getString(R.string.submit),
                                leadingIconData = LeadingIconData(
                                    iconDrawable = R.drawable.ic_send,
                                    iconContentDescription = R.string.submit
                                )
                            ) {
                                dismiss()
                            },
                            DialogButton.Secondary(getString(R.string.cancel)) {
                                dismiss()
                            }
                        )
                    )
                }
            }
        }
    }
}

package kr.co.fastcampus.part4plus.restaurantapp.features.detail.dialog

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4plus.restaurantapp.core.BaseDialogFragment
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.R
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.components.dialog.Default
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.components.dialog.DialogPopup
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.RestaurantAppTheme

@AndroidEntryPoint
class ShowMapDialogFragment : BaseDialogFragment() {

    val GOOGLE_MAP_BASE_URL = "https://www.google.com/maps/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.apply {
            isCancelable = true
            setCanceledOnTouchOutside(true)
            window?.setBackgroundDrawable(ColorDrawable(requireContext().getColor(android.R.color.transparent)))
        }

        val mapUrl = arguments?.getString("url") ?: ""
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                RestaurantAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    DialogPopup.Default(
                        title = stringResource(R.string.map_title),
                        bodyText = stringResource(R.string.map_message),
                        buttons = listOf(
                            DialogButton.Primary(getString(R.string.open)) {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(GOOGLE_MAP_BASE_URL + mapUrl)
                                    )
                                )
                            },
                            DialogButton.SecondaryBorderless(getString(R.string.cancel)) {
                                dismiss()
                            }
                        )
                    )
                }
            }
        }
    }
}

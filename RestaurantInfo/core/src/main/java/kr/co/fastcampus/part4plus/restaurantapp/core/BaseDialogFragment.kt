package kr.co.fastcampus.part4plus.restaurantapp.core

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import kr.co.fastcampus.part4plus.restaurantapp.core.viewmodel.ThemeViewModel

open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}

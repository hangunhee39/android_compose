package kr.co.fastcampus.part4plus.restaurantapp.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.fastcampus.part4plus.restaurantapp.core.viewmodel.ThemeViewModel

open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}

package com.example.movieinfo

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.movieinfo.features.common.viewmodel.ThemeViewModel

//theme 색을 적용하기 위해서
open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}
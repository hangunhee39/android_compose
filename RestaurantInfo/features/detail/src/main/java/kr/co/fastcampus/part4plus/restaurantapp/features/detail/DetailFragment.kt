package kr.co.fastcampus.part4plus.restaurantapp.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.restaurantapp.core.BaseFragment
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.RestaurantDetailScreen
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.DetailUiEffect
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.viewmodel.RestaurantDetailViewModel
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.navigation.safeNavigate
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.RestaurantAppTheme

//features.detail 에서 ui_components 를 사용할 수있는 이유: core 에서  api/path:ui_components 를 사용 했기 때문에
@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: RestaurantDetailViewModel by viewModels()
    //딥링크에서는 이 방식 사용안함
    //private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeUiEffects()
        init()
        return ComposeView(requireContext()).apply {
            setContent {
                RestaurantAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    RestaurantDetailScreen(
                        restaurantDetailState = viewModel.outputs.detailState.collectAsState(),
                        input = viewModel.inputs
                    )
                }
            }
        }
    }

    private fun init() {
        //argument 불러오기 (nav)
        val id = arguments?.getInt("id") ?: 0
        lifecycleScope.launch {
            viewModel.initDetail(id)
        }
    }

    private fun observeUiEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.outputs.detailUiEffect.collectLatest {
                    //딥링크(nav)
                    when (it) {
                        is DetailUiEffect.Back -> {
                            //navigateUp()을 안쓰는 이유 : stack이 쌓여서 여러번 백버튼을 눌루는 상항이 발생
                            //findNavController().navigateUp()
                            findNavController().safeNavigate(
                                "App://Feed"
                            )
                        }

                        is DetailUiEffect.OpenUrl -> {
                            findNavController().safeNavigate(
                                "App://Map/${it.url}"
                            )
                        }

                        is DetailUiEffect.RateRestaurant -> {
                            findNavController().safeNavigate(
                                "App://Rating/${it.restaurantName}/${it.rating}"
                            )
                        }
                    }
                }
            }
        }
    }
}

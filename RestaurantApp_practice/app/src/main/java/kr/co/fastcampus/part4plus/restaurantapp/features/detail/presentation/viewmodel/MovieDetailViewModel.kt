package kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.domain.usecase.IGetRestaurantDetailUseCase
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.input.IDetailViewModelInputs
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.DetailUiEffect
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.IDetailViewModelOutputs
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.RestaurantDetailState
import javax.inject.Inject


@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: IGetRestaurantDetailUseCase
): ViewModel(), IDetailViewModelInputs, IDetailViewModelOutputs {

    val inputs: IDetailViewModelInputs = this
    val outputs: IDetailViewModelOutputs = this

    private val _detailState: MutableStateFlow<RestaurantDetailState> =
        MutableStateFlow(RestaurantDetailState.Initial)
    override val detailState: StateFlow<RestaurantDetailState>
        get() = _detailState

    private val _detailUiEffect = MutableSharedFlow<DetailUiEffect>(replay = 0)
    override val detailUiEffect: SharedFlow<DetailUiEffect>
        get() = _detailUiEffect

    suspend fun initDetail(id: Int) {
        _detailState.value = RestaurantDetailState.Main(
            detailEntity = getRestaurantDetailUseCase(id)
        )
    }

    override fun goBackToFeed() {
        viewModelScope.launch {
            _detailUiEffect.emit(
                DetailUiEffect.Back
            )
        }
    }

    override fun googleMapClicked() {
        viewModelScope.launch {
//            if (detailState.value is RestaurantDetailState.Main) {
//                val value = detailState.value as RestaurantDetailState.Main
//                _detailUiEffect.emit(
//                    DetailUiEffect.OpenUrl(
//                        value.restaurantDetailEntity
//                    )
//                )
//            }
        }
    }

    override fun rateClicked() {
        viewModelScope.launch {
            if (detailState.value is RestaurantDetailState.Main) {
                val value = detailState.value as RestaurantDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.RateRestaurant(
                        restaurantName = value.detailEntity.name,
                        rating = value.detailEntity.rating
                    )
                )
            }
        }
    }
}

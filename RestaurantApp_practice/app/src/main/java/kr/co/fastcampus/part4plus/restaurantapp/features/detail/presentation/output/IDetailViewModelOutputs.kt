package kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModelOutputs {
    val detailState: StateFlow<RestaurantDetailState>
    val detailUiEffect: SharedFlow<DetailUiEffect>
}

sealed class DetailUiEffect {
    object Back: DetailUiEffect()

    data class OpenUrl(
        val url: String
    ): DetailUiEffect()

    data class RateRestaurant(
        val restaurantName: String,
        val rating: Float
    ): DetailUiEffect()
}

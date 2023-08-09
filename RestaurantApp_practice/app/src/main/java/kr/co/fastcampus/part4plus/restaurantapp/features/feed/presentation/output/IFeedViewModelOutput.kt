package kr.co.fastcampus.part4plus.restaurantapp.features.feed.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IFeedViewModelOutput {
    val feedState: StateFlow<FeedState>
    val feedUiEffect: SharedFlow<FeedUiEffect>
}

sealed class FeedUiEffect {
    data class OpenRestaurantDetail(val id: Int): FeedUiEffect()
    object OpenInfoDialog: FeedUiEffect()
}

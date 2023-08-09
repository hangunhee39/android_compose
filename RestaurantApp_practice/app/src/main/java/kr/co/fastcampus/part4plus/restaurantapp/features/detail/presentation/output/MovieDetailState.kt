package kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output

import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantDetailEntity

sealed class RestaurantDetailState {
    object Initial: RestaurantDetailState()
    class Main(val detailEntity: RestaurantDetailEntity): RestaurantDetailState()
}

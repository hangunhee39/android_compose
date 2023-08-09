package kr.co.fastcampus.part4plus.restaurantapp.features.detail.domain.usecase

import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantDetailEntity

interface IGetRestaurantDetailUseCase {
    suspend operator fun invoke(id: Int): RestaurantDetailEntity
}

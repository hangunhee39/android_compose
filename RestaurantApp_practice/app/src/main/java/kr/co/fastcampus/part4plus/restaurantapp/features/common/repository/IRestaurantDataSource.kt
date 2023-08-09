package kr.co.fastcampus.part4plus.restaurantapp.features.common.repository

import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantDetailEntity

interface IRestaurantDataSource {
    suspend fun getCategories(): EntityWrapper<List<CategoryEntity>>
    suspend fun getRestaurantDetail(id: Int): RestaurantDetailEntity
}

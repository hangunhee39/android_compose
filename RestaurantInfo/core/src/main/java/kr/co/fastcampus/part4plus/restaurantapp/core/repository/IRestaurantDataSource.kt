package kr.co.fastcampus.part4plus.restaurantapp.core.repository

import kr.co.fastcampus.part4plus.restaurantapp.core.entity.CategoryEntity
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.EntityWrapper
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.RestaurantDetailEntity

interface IRestaurantDataSource {
    suspend fun getCategories(): EntityWrapper<List<CategoryEntity>>
    suspend fun getRestaurantDetail(id: Int): RestaurantDetailEntity
}

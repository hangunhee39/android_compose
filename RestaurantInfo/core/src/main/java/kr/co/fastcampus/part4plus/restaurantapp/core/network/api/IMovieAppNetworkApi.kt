package kr.co.fastcampus.part4plus.restaurantapp.core.network.api

import kr.co.fastcampus.part4plus.restaurantapp.core.network.model.RestaurantResponse
import kr.co.fastcampus.part4plus.restaurantapp.libraries.network_contract.model.ApiResult

interface IRestaurantAppNetworkApi {
    suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>>
}

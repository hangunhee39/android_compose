package kr.co.fastcampus.part4plus.restaurantapp.features.common.network.api

import com.google.gson.reflect.TypeToken
import kr.co.fastcampus.part4plus.restaurantapp.features.common.network.model.RestaurantResponse
import kr.co.fastcampus.part4plus.restaurantapp.library.network.model.ApiResult
import kr.co.fastcampus.part4plus.restaurantapp.library.network.retrofit.NetworkRequestFactory
import javax.inject.Inject

class RestaurantAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory
): IRestaurantAppNetworkApi {
    override suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>> {
        return networkRequestFactory.create(
            url = "restaurants.json",
            type = object : TypeToken<List<RestaurantResponse>>(){}.type
        )
    }
}

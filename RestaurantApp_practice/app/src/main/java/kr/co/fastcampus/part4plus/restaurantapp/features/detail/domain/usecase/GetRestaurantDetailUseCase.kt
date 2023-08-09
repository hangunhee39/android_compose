package kr.co.fastcampus.part4plus.restaurantapp.features.detail.domain.usecase

import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantDetailEntity
import kr.co.fastcampus.part4plus.restaurantapp.features.common.repository.IRestaurantDataSource
import javax.inject.Inject

class GetRestaurantDetailUseCase @Inject constructor(
    private val dataSource: IRestaurantDataSource
) : IGetRestaurantDetailUseCase {
    override suspend fun invoke(id: Int): RestaurantDetailEntity {
        return dataSource.getRestaurantDetail(id)
    }
}

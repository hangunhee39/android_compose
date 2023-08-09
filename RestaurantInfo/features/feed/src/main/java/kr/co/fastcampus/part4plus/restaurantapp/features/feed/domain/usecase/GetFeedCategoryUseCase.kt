package kr.co.fastcampus.part4plus.restaurantapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.restaurantapp.core.entity.CategoryEntity
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.EntityWrapper
import kr.co.fastcampus.part4plus.restaurantapp.core.repository.IRestaurantDataSource
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IRestaurantDataSource
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories()
    }
}

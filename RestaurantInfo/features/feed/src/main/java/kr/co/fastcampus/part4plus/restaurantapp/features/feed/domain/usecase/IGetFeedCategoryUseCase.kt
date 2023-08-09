package kr.co.fastcampus.part4plus.restaurantapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.restaurantapp.core.entity.CategoryEntity
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.EntityWrapper

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(): EntityWrapper<List<CategoryEntity>>
}

package kr.co.fastcampus.part4plus.restaurantapp.core.mapper

import kr.co.fastcampus.part4plus.restaurantapp.core.entity.CategoryEntity
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.EntityWrapper
import kr.co.fastcampus.part4plus.restaurantapp.core.entity.RestaurantDetailEntity
import kr.co.fastcampus.part4plus.restaurantapp.core.network.model.RestaurantResponse
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.IStorage
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.constants.StorageKeys
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<RestaurantResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<RestaurantResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<RestaurantDetailEntity>()
                    .apply {
                        addAll(model.map { item -> item.toDetailEntity() })
                    }
                    .also {
                        storage.save(StorageKeys.RESTAURANT_LIST_KEY, it)
                    }
                    .toCategoryList()
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}

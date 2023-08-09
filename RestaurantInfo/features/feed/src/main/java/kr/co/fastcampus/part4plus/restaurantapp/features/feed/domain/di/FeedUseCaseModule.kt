package kr.co.fastcampus.part4plus.restaurantapp.features.feed.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.restaurantapp.features.feed.domain.usecase.GetFeedCategoryUseCase
import kr.co.fastcampus.part4plus.restaurantapp.features.feed.domain.usecase.IGetFeedCategoryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetRestaurantListUseCase(getRestaurantListUseCase: GetFeedCategoryUseCase): IGetFeedCategoryUseCase
}

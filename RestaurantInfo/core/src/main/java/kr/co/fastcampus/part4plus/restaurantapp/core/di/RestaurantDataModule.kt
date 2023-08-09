package kr.co.fastcampus.part4plus.restaurantapp.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.restaurantapp.core.network.api.IRestaurantAppNetworkApi
import kr.co.fastcampus.part4plus.restaurantapp.core.network.api.RestaurantAppNetworkApi
import kr.co.fastcampus.part4plus.restaurantapp.core.repository.IRestaurantDataSource
import kr.co.fastcampus.part4plus.restaurantapp.core.repository.RestaurantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantDataModule {

    @Singleton
    @Binds
    abstract fun bindRestaurantRepository(restaurantRepository: RestaurantRepository): IRestaurantDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: RestaurantAppNetworkApi): IRestaurantAppNetworkApi

}

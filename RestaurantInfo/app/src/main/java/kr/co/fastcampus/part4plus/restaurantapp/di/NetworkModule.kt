package kr.co.fastcampus.part4plus.restaurantapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.restaurantapp.libraries.network.retrofit.NetworkRequestFactoryImpl
import kr.co.fastcampus.part4plus.restaurantapp.libraries.network_contract.api.NetworkRequestFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun bindNetworkRequestFactory(networkRequestFactory: NetworkRequestFactoryImpl): NetworkRequestFactory =
        networkRequestFactory

}

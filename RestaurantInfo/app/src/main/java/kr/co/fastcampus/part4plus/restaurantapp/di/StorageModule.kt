package kr.co.fastcampus.part4plus.restaurantapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.StorageManager
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.helpers.DataConverter
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.helpers.DataEncoding
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.prefs.SharedPrefsStorageProvider
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.prefs.StorageProvider
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.IStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding
    ): IStorage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider =
        provider

}

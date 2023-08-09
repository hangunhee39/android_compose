package kr.co.fastcampus.part4plus.restaurantapp.library.storage.usecases

import kr.co.fastcampus.part4plus.restaurantapp.library.storage.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(
    private val storage: IStorage
) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}

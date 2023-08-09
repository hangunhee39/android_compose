package kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.usecases

import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(
    private val storage: IStorage
) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}

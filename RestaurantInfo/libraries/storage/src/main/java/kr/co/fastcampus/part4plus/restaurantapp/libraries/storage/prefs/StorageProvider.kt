package kr.co.fastcampus.part4plus.restaurantapp.libraries.storage.prefs

interface StorageProvider {
    fun save(key: String, value: String): Boolean
    fun get(key: String, default: String? = null): String?
    fun remove(key: String): Boolean
    fun clear()
}
